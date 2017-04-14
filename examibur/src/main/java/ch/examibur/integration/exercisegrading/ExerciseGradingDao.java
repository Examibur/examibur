package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;

public interface ExerciseGradingDao {
  /**
   * @param exerciseSolutionId
   *          the id of the exerciseSolution to get the grading for
   * @param state
   *          the ExamState the ExerciseSolution was created in, e.g. REVIEW or CORRECTION
   * @return the ExerciseGrading if it was found, null otherwise.
   */
  ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state);
}
