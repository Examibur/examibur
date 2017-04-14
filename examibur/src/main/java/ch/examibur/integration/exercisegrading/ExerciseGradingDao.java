package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;

@FunctionalInterface
public interface ExerciseGradingDao {
  /**
   * @param exerciseSolutionId
   *          the id of the exerciseSolution to get the grading for. The exerciseSolution with the
   *          given id must exist, otherwise <code>null</code> is returned.
   * @param state
   *          the ExamState the ExerciseSolution was created in, e.g. REVIEW or CORRECTION
   * @return the ExerciseGrading if it was found, null otherwise.
   */
  ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state);
}
