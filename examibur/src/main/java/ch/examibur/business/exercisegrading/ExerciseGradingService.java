package ch.examibur.business.exercisegrading;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
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

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. if the id is < 0, an IllegalArgumentException
   *          is thrown. If it doesn't exist, null is returned.
   * @param comment
   *          the comment which describes the solution
   * @param reasoning
   *          the reasoning which describes how much points were given
   * @param points
   *          the number of points given for the solution
   * @param gradingAuthor
   *          the author of this grading
   * @throws AuthorizationException
   *           if the user is not authorized.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  void addGrading(long exerciseSolutionId, String comment, String reasoning, double points)
      throws NotFoundException, IOException, AuthorizationException;
}
