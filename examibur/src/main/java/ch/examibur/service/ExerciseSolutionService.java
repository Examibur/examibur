package ch.examibur.service;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExerciseSolutionOverview;
import java.util.List;

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
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}.
   * @return a list of {@link ExerciseSolutionOverview} with an {@link ExerciseSolution} and the
   *         current graded points that correspond to the {@link ExamParticipation} with the given
   *         id. If there are none found, an empty list is returned.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the given examParticipationId < 0. throws
   *           {@link NotFoundException} if the {@link ExamParticipation} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  List<ExerciseSolutionOverview> getExerciseSolutionsForExamParticipation(long examParticipationId)
      throws ExamiburException;

  /**
   * @param mode
   *          the {@link BrowseSolutionsMode} in which the new {@link ExerciseSolution} should be
   *          queried.
   * @param resourceId
   *          the id of the current resource (BY_EXERCISE(S) => exerciseId, BY_PARTICIPATION(S) =>
   *          participationId).
   * @return the first unprocessed {@link ExerciseSolution} according to the given
   *         {@link BrowseSolutionsMode}. If the last exerciseSolution is reached, null will be
   *         returned.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the resourceId is < 0 or the mode is
   *           null. throws {@link NotFoundException} if the record with the given resourceId is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  ExerciseSolution getFirstExerciseSolution(BrowseSolutionsMode mode, long resourceId)
      throws ExamiburException;

  /**
   * @param mode
   *          the {@link BrowseSolutionsMode} in which the new {@link ExerciseSolution} should be
   *          queried.
   * @param currentExerciseSolutionId
   *          the id of the current {@link ExerciseSolution}.
   * @return the next unprocessed {@link ExerciseSolution} according to the given
   *         {@link BrowseSolutionsMode}. If the last exerciseSolution is reached, null will be
   *         returned.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the id is < 0 or the mode is null. throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized to access
   *           this {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode mode, long currentExerciseSolutionId)
      throws ExamiburException;
}
