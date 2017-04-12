package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;

public interface ExerciseGradingDao {
  ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state);
}
