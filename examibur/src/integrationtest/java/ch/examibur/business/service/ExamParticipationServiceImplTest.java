package ch.examibur.business.service;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.model.ExamParticipantOverview;
import java.util.List;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExamParticipationServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;
  private static final long SUM_EXAM_GRADINGS_EXAM_4 = 3L;

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExamParticipationService examParticipationService = INJECTOR
      .getInstance(ExamParticipationService.class);

  @Test
  public void testGetExamParticipantsOverview() {
    List<ExamParticipantOverview> examParticipantOverviewList = examParticipationService
        .getExamParticipantsOverview(4);
    Assert.assertEquals(3, examParticipantOverviewList.size());
    for (ExamParticipantOverview examParticipantOverview : examParticipantOverviewList) {
      if (examParticipantOverview.getExamParticipation().getPseudonym()
          .equals("Anonymes Einhorn")) {  //ParticipantionId: 1
        testExamParticipantOverview(examParticipantOverview, 5, 5.17, 3d, SUM_EXAM_GRADINGS_EXAM_4,
            "100.00%");
      } else if (examParticipantOverview.getExamParticipation().getPseudonym()
          .equals("Anonyme Gazelle")) {   //ParticipantionId: 2
        testExamParticipantOverview(examParticipantOverview, 4, 4.33, 2d, SUM_EXAM_GRADINGS_EXAM_4,
            "66.67%");
      } else if (examParticipantOverview.getExamParticipation().getPseudonym()
          .equals("Anonymes Zebra")) {    //ParticipantionId: 3
        testExamParticipantOverview(examParticipantOverview, 5, 5.17, 1d, SUM_EXAM_GRADINGS_EXAM_4,
            "33.33%");
      } else {
        Assert.fail("unknown participant in exam participation list");
      }
    }
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
    Assert.assertEquals(expectedGrading, examParticipantOverview.getGrading(), DOUBLE_DELTA);
    Assert.assertEquals(completedExamGradings / totalExamGradings,
        examParticipantOverview.getProgress(), DOUBLE_DELTA);
    Assert.assertEquals(expectedFormattedProgress, examParticipantOverview.getFormattedProgress());
  }

}
