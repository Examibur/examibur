package ch.examibur.service;

import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;

public interface ExerciseGradingService {
  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If it doesn't exist, null is returned.
   * @return the grading for the {@link ExerciseSolution} with the given id, null if there is no
   *         corresponding grading.
   * @throws ExamiburException
   *           throws {@link AuthorizationException} if the user is not authorized. throws
   *           {@link CommunicationException} if an exception during the communication occurs.
   */
  ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId) throws ExamiburException;

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If it doesn't exist, null is returned.
   * @return the review for the {@link ExerciseSolution} with the given id, null if there is no
   *         corresponding review.
   * @throws ExamiburException
   *           throws {@link AuthorizationException} if the user is not authorized. throws
   *           {@link CommunicationException} if an exception during the communication occurs.
   */
  ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId) throws ExamiburException;

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If the id is < 0, an
   *          {@link IllegalArgumentException} is thrown.
   * @param comment
   *          the comment which describes the solution
   * @param reasoning
   *          the reasoning which describes how much points were given
   * @param points
   *          the number of points given for the solution
   * @throws NotFoundException
   *           if the {@link ExerciseSolution} with the given id is not found.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the id is < 0. Throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. Throws {@link AuthorizationException} if the user is not authorized. Throws
   *           {@link CommunicationException} if an exception during the communication occurs.
   *           Throws {@link IllegalOperationException} if the exam is not in CORRECTION nor in
   *           REVIEW. Throws {@link IllegalOperationException} if there is already a grading for
   *           this {@link ExerciseSolution} in the same state.
   */
  void addGrading(long exerciseSolutionId, String comment, String reasoning, double points)
      throws ExamiburException;
}
