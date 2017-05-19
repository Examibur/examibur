package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.Exercise;
import ch.examibur.domain.ExerciseSolution;
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
   * @param exerciseId
   *          the id of the {@link Exercise}
   * @return a list of {@link ExerciseSolution} corresponding to the {@link Exercise} with the given
   *         id.
   */
  List<ExerciseSolution> getExerciseSolutionsForExercise(long exerciseId);

  /**
   * @param participationId
   *          the id of the {@link ExamParticipation}.
   * @return the first {@link ExerciseSolution} of the given {@link ExamParticipation} ordered by
   *         exerciseId.
   */
  ExerciseSolution getFirstExerciseSolutionFromParticipation(long participationId);

  /**
   * @param exerciseId
   *          the id of the {@link Exercise}.
   * @return the first {@link ExerciseSolution} of the given {@link Exercise} ordered by
   *         participationId.
   */
  ExerciseSolution getFirstExerciseSolutionFromExercise(long exerciseId);

  /**
   * @param currentExerciseSolutionId
   *          the id of the current {@link ExerciseSolution}. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the {@link ExerciseSolution} of the same {@link Exercise} from the next
   *         {@link ExamParticipation} ordered by participationId. If the last exerciseSolution is
   *         reached, null will be returned.
   */
  ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId);

  /**
   * @param currentExerciseSolutionId
   *          the id of the current {@link ExerciseSolution}. If the record is not found, a
   *          {@link NoResultException} is thrown.
   * @return the {@link ExerciseSolution} of the next {@link Exercise} within the same
   *         {@link ExamParticipation} ordered by exerciseId. If the last exerciseSolution is
   *         reached, null will be returned.
   */
  ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId);
}
