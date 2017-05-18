package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.aggregation.ExamPerformance;
import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import ch.examibur.service.ExamReportService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExamReportServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;

  @Rule
  public final DatabaseResource res = new DatabaseResource();

  private final ExamReportService examReportService =
      IntegrationTestUtil.getInjector().getInstance(ExamReportService.class);

  @Test
  public void testIsReportRetrievalPossible() throws ExamiburException {
    Assert.assertTrue(examReportService.isReportRetrievalPossible(6L));
  }

  @Test
  public void testIsReportRetrievalPossibleFalse() throws ExamiburException {
    Assert.assertFalse(examReportService.isReportRetrievalPossible(1L));
  }

  @Test
  public void testGetExamPerformanceReport() throws ExamiburException {
    ExamPerformance examPerformance = examReportService.getExamPerformanceReport(6L);
    Assert.assertEquals(4.33D, examPerformance.getAverageGrade(), DOUBLE_DELTA);
    Assert.assertEquals(4.33D, examPerformance.getMedianGrade(), DOUBLE_DELTA);
    Assert.assertEquals(3, examPerformance.getIncludedParticipations());
    Assert.assertEquals(3, examPerformance.getTotalParticipations());
  }

  @Test
  public void testGetExamPerformanceReportMissingGradings() throws ExamiburException {
    ExamPerformance examPerformance = examReportService.getExamPerformanceReport(8L);
    Assert.assertEquals(2.67D, examPerformance.getAverageGrade(), DOUBLE_DELTA);
    Assert.assertEquals(2.67D, examPerformance.getMedianGrade(), DOUBLE_DELTA);
    Assert.assertEquals(1, examPerformance.getIncludedParticipations());
    Assert.assertEquals(3, examPerformance.getTotalParticipations());
  }

  @Test(expected = NotFoundException.class)
  public void testGetExamPerformanceReportWithNonexistentExamId() throws ExamiburException {
    examReportService.getExamPerformanceReport(0L);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExamPerformanceReportWithNegativeExamId() throws ExamiburException {
    examReportService.getExamPerformanceReport(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExamPerformanceReportWithoutExercises() throws ExamiburException {
    examReportService.getExamPerformanceReport(1L);
  }

  @Test
  public void testGetPassedParticipationComparisonReport() throws ExamiburException {
    PassedParticipationComparison passedComparison =
        examReportService.getPassedParticipationComparisonReport(6L);
    Assert.assertEquals(2 / 3D * 100,
        passedComparison.getPercentageOfSuccessfulParticipations(), DOUBLE_DELTA); // 66.66...
    Assert.assertEquals(1 / 3D * 100,
        passedComparison.getPercentageOfUnsuccessfulParticipations(), DOUBLE_DELTA); // 33.33...
  }

  @Test
  public void testGetPassedParticipationComparisonReportMissingGradings() throws ExamiburException {
    PassedParticipationComparison passedParticipationComparison =
        examReportService.getPassedParticipationComparisonReport(8L);
    Assert.assertEquals(0, passedParticipationComparison.getPercentageOfSuccessfulParticipations(),
        DOUBLE_DELTA);
    Assert.assertEquals(100,
        passedParticipationComparison.getPercentageOfUnsuccessfulParticipations(), DOUBLE_DELTA);
  }

  @Test(expected = NotFoundException.class)
  public void testGetPassedParticipationComparisonReportWithNonexistentExamId()
      throws ExamiburException {
    examReportService.getPassedParticipationComparisonReport(0L);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetPassedParticipationComparisonReportWithNegativeExamId()
      throws ExamiburException {
    examReportService.getPassedParticipationComparisonReport(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetPassedParticipationComparisonReportWithoutExercises()
      throws ExamiburException {
    examReportService.getPassedParticipationComparisonReport(1L);
  }

  @Test
  public void testGetExerciseAverageMaxPointsComparisonReport() throws ExamiburException {
    List<ExerciseAverageMaxPointsComparison> comparisonList =
        examReportService.getExerciseAverageMaxPointsComparisonReport(6L);
    Assert.assertEquals(3, comparisonList.size());
    for (ExerciseAverageMaxPointsComparison comparison : comparisonList) {
      if (comparison.getTitle().equals("Verordnung gegen Verfassung")) {
        testExerciseAverageMaxPointsComparison(comparison, 2D, 4 / 3D); // 1.33...
      } else if (comparison.getTitle().equals("Datenbearbeitung")) {
        testExerciseAverageMaxPointsComparison(comparison, 2D, 1D);
      } else if (comparison.getTitle().equals("Zuständige Aufsichtsbehörde")) {
        testExerciseAverageMaxPointsComparison(comparison, 2D, 5 / 3D); // 1.77...
      } else {
        Assert.fail("unknown ExerciseAverageMaxPointsComparison in list: " + comparison.getTitle());
      }
    }
  }

  @Test
  public void testGetExerciseAverageMaxPointsComparisonReportMissingGradings()
      throws ExamiburException {
    List<ExerciseAverageMaxPointsComparison> comparisonList =
        examReportService.getExerciseAverageMaxPointsComparisonReport(8L);
    Assert.assertEquals(3, comparisonList.size());
    for (ExerciseAverageMaxPointsComparison comparison : comparisonList) {
      if (comparison.getTitle().equals("AES-CBC Disk Encryption")) {
        testExerciseAverageMaxPointsComparison(comparison, 5D, 1D);
      } else if (comparison.getTitle().equals("XTS-AES Speicherplatz Ausnutzung")) {
        testExerciseAverageMaxPointsComparison(comparison, 5D, 5D);
      } else if (comparison.getTitle().equals("XTS-AES Verschiebung")) {
        testExerciseAverageMaxPointsComparison(comparison, 5D, 0D);
      } else {
        Assert.fail("unknown ExerciseAverageMaxPointsComparison in list: " + comparison.getTitle());
      }
    }
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseAverageMaxPointsComparisonReportWithNonexistentExamId()
      throws ExamiburException {
    examReportService.getExerciseAverageMaxPointsComparisonReport(0L);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExerciseAverageMaxPointsComparisonReportWithNegativeExamId()
      throws ExamiburException {
    examReportService.getExerciseAverageMaxPointsComparisonReport(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseAverageMaxPointsComparisonReportWithoutExercises()
      throws ExamiburException {
    examReportService.getExerciseAverageMaxPointsComparisonReport(1L);
  }

  private void testExerciseAverageMaxPointsComparison(
      ExerciseAverageMaxPointsComparison exerciseAverageMaxPointsComparison,
      double expectedMaxPoints, double expectedAveragePoints) {
    Assert.assertEquals(expectedMaxPoints, exerciseAverageMaxPointsComparison.getMaxPoints(),
        DOUBLE_DELTA);
    Assert.assertEquals(expectedAveragePoints,
        exerciseAverageMaxPointsComparison.getAveragePoints(), DOUBLE_DELTA);
  }

}
