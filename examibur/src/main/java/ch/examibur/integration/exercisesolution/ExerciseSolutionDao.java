package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.ExamiburException;
import java.util.List;
import javax.persistence.NoResultException;

public interface ExerciseSolutionDao {
  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the {@link ExerciseSolution} with the given id.
   */
  ExerciseSolution getExerciseSolution(long exerciseSolutionId);

  /**
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}. If it doesn't exist, a
   *          {@link NoResultException} is thrown.
   * @return a list of {@link ExerciseSolution} corresponding to the {@link ExamParticipation} with
   *         the given id.
   */
  List<ExerciseSolution> getExerciseSolutionsForExamParticipation(long examParticipationId);

  /**
   * @param mode
   *          the {@link BrowseSolutionsMode} in which the new {@link ExerciseSolution} should be
   *          queried.
   * @param resourceId
   *          the id of the current resource (BY_EXERCISE(S) => exerciseId, BY_PARTICIPATION(S) =>
   *          participationId). If the record is not found, a {@link NoResultException} is thrown.
   * @return the first unprocessed {@link ExerciseSolution} according to the given
   *         {@link BrowseSolutionsMode}. If the last exerciseSolution is reached, null will be
   *         returned.
   */
  ExerciseSolution getFirstExerciseSolution(BrowseSolutionsMode mode, long resourceId)
      throws ExamiburException;

  /**
   * @param mode
   *          the {@link BrowseSolutionsMode} in which the new {@link ExerciseSolution} should be
   *          queried.
   * @param currentExerciseSolutionId
   *          the id of the current {@link ExerciseSolution}. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the next unprocessed {@link ExerciseSolution} according to the given
   *         {@link BrowseSolutionsMode}. If the last exerciseSolution is reached, null will be
   *         returned.
   */
  ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode mode, long currentExerciseSolutionId)
      throws ExamiburException;
}
