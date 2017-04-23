package ch.examibur.business.exercisesolution;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.ExerciseSolution;
import java.io.IOException;

public interface ExerciseSolutionService {
  /**
   * @param exerciseSolutionId
   *          the id of the exercisesolution. If the id is < 0, a {@link IllegalArgumentException}
   *          is thrown.
   * @return the {@link ExerciseSolution} with the given id.
   * @throws NotFoundException
   *           if the {@link ExerciseSolution} with the given id is not found.
   * @throws AuthorizationException
   *           if the user is not authorized to access this {@link ExerciseSolution}
   * @throws IOException
   *           if an exception during the communication occurs
   */
  ExerciseSolution getExerciseSolution(long exerciseSolutionId)
      throws NotFoundException, AuthorizationException, IOException;

  /**
   * @param currentExerciseSolutionId
   *          the id of the current exercisesolution. If the id is < 0, a
   *          {@link IllegalArgumentException} is thrown.
   * @return the exerciseSolutionId from the same exercise of the next participation ordered by the
   *         examParticipationId. If the last exerciseSolution is reached, 0 will be returned.
   * @throws NotFoundException
   *           if the {@link ExerciseSolution} with the given id is not found.
   * @throws AuthorizationException
   *           if the user is not authorized to access this {@link ExerciseSolution}
   * @throws IOException
   *           if an exception during the communication occurs
   */
  long getExerciseSolutionIdFromNextParticipation(long currentExerciseSolutionId)
      throws NotFoundException, AuthorizationException, IOException;
}
