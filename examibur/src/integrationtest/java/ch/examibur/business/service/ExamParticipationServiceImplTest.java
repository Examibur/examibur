package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExamParticipation;
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
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExamParticipationServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;
  private static final long SUM_EXAM_GRADINGS_EXAM_4 = 3L;

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Rule
  public final DatabaseResource res = new DatabaseResource();
  private final ExamParticipationService examParticipationService = IntegrationTestUtil
      .getInjector().getInstance(ExamParticipationService.class);

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
  public void testGetExamParticipantsOverview() {
    List<ExamParticipantOverview> examParticipantOverviewList = examParticipationService
        .getExamParticipantsOverview(4);
    Assert.assertEquals(3, examParticipantOverviewList.size());

    Collections.sort(examParticipantOverviewList,
        (ep1, ep2) -> Long.compare(ep2.getId(), ep1.getId()));
    testExamParticipantOverview(examParticipantOverviewList.get(0), 4, 4.33, 3d,
        SUM_EXAM_GRADINGS_EXAM_4, "100.00%"); // ParticipantionId: 1 (Anonymes Einhorn)
    testExamParticipantOverview(examParticipantOverviewList.get(1), 3, 3.5, 2d,
        SUM_EXAM_GRADINGS_EXAM_4, "66.67%"); // ParticipantionId: 2 (Anonyme Gazelle)
    testExamParticipantOverview(examParticipantOverviewList.get(2), 5, 5.17, 1d,
        SUM_EXAM_GRADINGS_EXAM_4, "33.33%"); // ParticipantionId: 3 (Anonymes Zebra)
  }

  @Test
  public void testGetExamParticipantsOverviewWithNoGradings() {
    List<ExamParticipantOverview> examParticipantOverviewList = examParticipationService
        .getExamParticipantsOverview(1);
    Assert.assertEquals(0, examParticipantOverviewList.size());
  }

  @Test
  public void testGetExamParticipantsOverviewWithNonexistentExamId() {
    List<ExamParticipantOverview> examParticipantOverviewList = examParticipationService
        .getExamParticipantsOverview(-1);
    Assert.assertEquals(0, examParticipantOverviewList.size());
  }

  private void testExamParticipantOverview(ExamParticipantOverview examParticipantOverview,
      double expectedTotalPoints, double expectedGrading, double completedExamGradings,
      double totalExamGradings, String expectedFormattedProgress) {
    Assert.assertEquals(expectedTotalPoints, examParticipantOverview.getTotalPoints(),
        DOUBLE_DELTA);
    Assert.assertEquals(expectedGrading, examParticipantOverview.getGrading().get(), DOUBLE_DELTA);
    Assert.assertEquals(completedExamGradings / totalExamGradings,
        examParticipantOverview.getProgress(), DOUBLE_DELTA);
    Assert.assertEquals(expectedFormattedProgress, examParticipantOverview.getFormattedProgress());
  }

}
