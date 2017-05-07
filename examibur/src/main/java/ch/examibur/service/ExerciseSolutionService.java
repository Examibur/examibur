package ch.examibur.service;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;

public interface ExerciseSolutionService {
  /**
   * @param exerciseSolutionId
   *          the id of the exercisesolution.
   * @return the {@link ExerciseSolution} with the given id.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the given exercisesolutionId < 0. throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  ExerciseSolution getExerciseSolution(long exerciseSolutionId) throws ExamiburException;

  /**
   * @param currentExerciseSolutionId
   *          the id of the current exercisesolution.
   * @return the exerciseSolution of the same exercise from the next participation ordered by the
   *         id. If the last exerciseSolution is reached, null will be returned.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the id is < 0. throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId)
      throws ExamiburException;

  /**
   * @param currentExerciseSolutionId
   *          the id of the current exercisesolution.
   * @return the exerciseSolution of the next exercise within the same participation ordered by the
   *         id. If the last exerciseSolution is reached, null will be returned.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the id is < 0. throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId)
      throws ExamiburException;
}
