package ch.examibur.business.exercisegrading;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import java.io.IOException;

public interface ExerciseGradingService {
  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If it doesn't exist, null is returned.
   * @return the grading for the {@link ExerciseSolution} with the given id, null if there is no
   *         corresponding grading.
   * @throws AuthorizationException
   *           if the user is not authorized.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId)
      throws AuthorizationException, IOException;

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If it doesn't exist, null is returned.
   * @return the review for the {@link ExerciseSolution} with the given id, null if there is no
   *         corresponding review.
   * @throws AuthorizationException
   *           if the user is not authorized.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId)
      throws AuthorizationException, IOException;
}
