package ch.examibur.integration.report;

import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import java.util.List;

public interface ExamReportDao {
  
  public PassedParticipationComparison getPassedParticipationComparisonReport(long examId);

  public List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId);

}
