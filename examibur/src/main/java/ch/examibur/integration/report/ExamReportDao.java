package ch.examibur.integration.report;

import ch.examibur.domain.aggregation.ExamPerformance;
import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import java.util.List;

public interface ExamReportDao {
  
  PassedParticipationComparison getPassedParticipationComparisonReport(long examId);

  List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId);
  
  ExamPerformance getExamPerformanceReport(long examId);
  
  boolean isReportRetrievalPossible(long examId);

}
