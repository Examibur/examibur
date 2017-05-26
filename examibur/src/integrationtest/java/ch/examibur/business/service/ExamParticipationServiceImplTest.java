package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.ExamState;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExamParticipantOverview;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExamParticipationServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;
  private static final double SUM_EXAM_GRADINGS_EXAM_4 = 3D;
  private static final double SUM_EXAM_GRADINGS_EXAM_7 = 3D;
  private static final double SUM_EXAM_GRADINGS_EXAM_8 = 3D;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Rule
  public final DatabaseResource res = new DatabaseResource();
  private final ExamParticipationService examParticipationService =
      IntegrationTestUtil.getInjector().getInstance(ExamParticipationService.class);

  @Test
  public void testGetExamParticipation() throws ExamiburException, ParseException {
    ExamParticipation examParticipation = examParticipationService.getExamParticipation(17L);
    Assert.assertEquals(17L, examParticipation.getId());
    Assert.assertEquals(new Date(dateFormat.parse("2017-01-27").getTime()),
        examParticipation.getparticipationDate());
    Assert.assertEquals("Anonymer Elefant", examParticipation.getPseudonym());
    Assert.assertNotNull(examParticipation.getExam());
    Assert.assertNotNull(examParticipation.getParticipant());
    Assert.assertEquals(8L, examParticipation.getExam().getId());
    Assert.assertEquals(2L, examParticipation.getParticipant().getId());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExamParticipationWithNegativeId() throws ExamiburException {
    examParticipationService.getExamParticipation(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExamParticipationWithNonexistentId() throws ExamiburException {
    examParticipationService.getExamParticipation(200L);
  }

  @Test
  public void testGetExamParticipantOverviewList() throws ExamiburException {
    List<ExamParticipantOverview> examParticipantOverviewList =
        examParticipationService.getExamParticipantOverviewList(4);
    Assert.assertEquals(3, examParticipantOverviewList.size());

    Collections.sort(examParticipantOverviewList,
        (ep1, ep2) -> Long.compare(ep2.getId(), ep1.getId()));
    // ParticipantionId: 6 (Anonymes Einhorn)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(0), 4D, Optional.of(4.33D),
        3D, SUM_EXAM_GRADINGS_EXAM_4, "100.00%", ExamState.APPROVAL);
    // ParticipantionId: 5 (Anonyme Gazelle)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(1), 3D, Optional.of(3.5D), 2D,
        SUM_EXAM_GRADINGS_EXAM_4, "66.67%", ExamState.APPROVAL);
    // ParticipantionId: 4 (Anonymes Zebra)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(2), 5D, Optional.of(5.17D),
        1D, SUM_EXAM_GRADINGS_EXAM_4, "33.33%", ExamState.APPROVAL);
  }

  @Test
  public void testGetExamParticipantOverviewListWithMissingGradings() throws ExamiburException {
    List<ExamParticipantOverview> examParticipantOverviewList =
        examParticipationService.getExamParticipantOverviewList(8);
    Assert.assertEquals(3, examParticipantOverviewList.size());

    Collections.sort(examParticipantOverviewList,
        (ep1, ep2) -> Long.compare(ep2.getId(), ep1.getId()));
    // ParticipantionId: 18 (Anonymer Flamingo)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(0), 2D, Optional.empty(), 0D,
        SUM_EXAM_GRADINGS_EXAM_8, "0.00%", ExamState.CORRECTION);
    // ParticipantionId: 17 (Anonymer Elefant)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(1), 0D, Optional.empty(), 0D,
        SUM_EXAM_GRADINGS_EXAM_8, "0.00%", ExamState.CORRECTION);
    // ParticipantionId: 16 (Anonymer Panda)
    testSingleExamParticipantOverview(examParticipantOverviewList.get(2), 5D, Optional.of(2.67D),
        3D, SUM_EXAM_GRADINGS_EXAM_8, "100.00%", ExamState.CORRECTION);
  }

  @Test
  public void testGetExamParticipantOverviewListWithNoParticipations() throws ExamiburException {
    List<ExamParticipantOverview> examParticipantOverviewList =
        examParticipationService.getExamParticipantOverviewList(1);
    Assert.assertEquals(0, examParticipantOverviewList.size());
  }

  @Test
  public void testGetExamParticipantOverviewListWithNonexistentExamId() throws ExamiburException {
    List<ExamParticipantOverview> examParticipantOverviewList =
        examParticipationService.getExamParticipantOverviewList(-1);
    Assert.assertEquals(0, examParticipantOverviewList.size());
  }

  @Test
  public void testGetExamParticipantOverview() throws ExamiburException {
    ExamParticipantOverview examParticipantOverview =
        examParticipationService.getExamParticipantOverview(13);
    // ParticipantionId: 13 (Anonymes Kamel)
    testSingleExamParticipantOverview(examParticipantOverview, 8D, Optional.of(5D), 3D,
        SUM_EXAM_GRADINGS_EXAM_7, "100.00%", ExamState.CORRECTION);
  }

  @Test
  public void testGetExamParticipantOverviewWithNoGrading() throws ExamiburException {
    ExamParticipantOverview examParticipantOverview =
        examParticipationService.getExamParticipantOverview(17);
    // ParticipantionId: 17 (Anonymer Elefant)
    testSingleExamParticipantOverview(examParticipantOverview, 0D, Optional.empty(), 0D,
        SUM_EXAM_GRADINGS_EXAM_8, "0.00%", ExamState.CORRECTION);
  }

  private void testSingleExamParticipantOverview(ExamParticipantOverview examParticipantOverview,
      double expectedTotalPoints, Optional<Double> expectedGrading, double completedExamGradings,
      double totalExamGradings, String expectedFormattedProgress, ExamState expectedExamState) {
    Assert.assertEquals(expectedTotalPoints, examParticipantOverview.getTotalPoints(),
        DOUBLE_DELTA);
    if (expectedGrading.isPresent()) {
      Assert.assertEquals(expectedGrading.get(), examParticipantOverview.getGrading().get(),
          DOUBLE_DELTA);
    } else {
      Assert.assertEquals(expectedGrading, examParticipantOverview.getGrading());
    }
    Assert.assertEquals(completedExamGradings / totalExamGradings,
        examParticipantOverview.getProgress(), DOUBLE_DELTA);
    Assert.assertEquals(expectedFormattedProgress, examParticipantOverview.getFormattedProgress());
    Assert.assertEquals(expectedExamState, examParticipantOverview.getExamState());
  }

}
