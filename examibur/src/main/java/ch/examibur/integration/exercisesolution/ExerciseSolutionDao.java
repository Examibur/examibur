package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExerciseSolution;
import javax.persistence.NoResultException;

public interface ExerciseSolutionDao {
  /**
   * @param exerciseSolutionId
   *          the id of the exerciseSolution. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the {@link ExerciseSolution} with the given id.
   */
  ExerciseSolution getExerciseSolution(long exerciseSolutionId);

  /**
   * @param currentExerciseSolutionId
   *          the id of the current exerciseSolution. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the exerciseSolution of the same exercise from the next participation ordered by the
   *         id. If the last exerciseSolution is reached, null will be returned.
   */
  ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId);

  /**
   * @param currentExerciseSolutionId
   *          the id of the current exerciseSolution. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the exerciseSolution of the next exercise within the same participation ordered by the
   *         id. If the last exerciseSolution is reached, null will be returned.
   */
  ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId);
}
