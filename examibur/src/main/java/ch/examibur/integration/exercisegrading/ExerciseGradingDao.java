package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.User;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution}.
   * @return The currently graded points for an {@link ExerciseSolution} as an {@link Optional} of
   *         type Double. It has its value present if there is a at least one grading for the
   *         {@link ExerciseSolution}, otherwise the value is not present.
   */
  Optional<Double> getPointsOfExerciseSolution(long exerciseSolutionId);

  /**
   * @param examParticipationId
   *          the id of the participation to get the total points for a specific participant
   * @return the total points of a all exam gradings for a specific participant.
   */
  double getTotalPointsOfExamGradings(long examParticipationId);

  /**
   * @see ch.examibur.integration.exercisegrading.ExerciseGradingDao
   *      #getTotalPointsOfExamGradings(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  double getTotalPointsOfExamGradings(long examParticipationId, EntityManager entityManager);

  /**
   * @param examId
   *          the id of the exam
   * @param participationId
   *          the id of the participation to get the progress for a specific participant
   * @return the progress of a all exam gradings for a specific participant.
   */
  double getProgressOfExamGradings(long examId, long participationId);

  /**
   * @param exerciseId
   *          the id of the exercise to get all corresponding exercise gradings
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   * @return the average points of all exercises gradings for one exercise.
   */
  double getAveragePointsOfExercise(long exerciseId, EntityManager entityManager);

  /**
   * @param examId
   *          the id of the exam
   * @param participationId
   *          the id of the participation to check if all exercises are graded
   * @return true, if all exercises for a specific participant are graded.
   */
  boolean checkIfAllExercisesAreGraded(long examId, long participationId);

  /**
   * @see ch.examibur.integration.exercisegrading.ExerciseGradingDao
   *      #checkIfAllExercisesAreGraded(long, long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  boolean checkIfAllExercisesAreGraded(long examId, long participationId,
      EntityManager entityManager);

  /**
   * Adds a new grading to an {@link ExerciseSolution}. Throws an {@link IllegalArgumentException}
   * if points are not in range. Throws an {@link IllegalStateException} if the exam is in the wrong
   * state or the grading already exists. Throws a {@link NoResultException} if the
   * {@link ExerciseSolution} doesnt exist.
   * 
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution} which is graded
   * @param comment
   *          the comment which describes the solution
   * @param reasoning
   *          the reasoning which describes how much points were given.
   */
  void addGrading(long exerciseSolutionId, String comment, String reasoning, double points,
      User gradingAuthor);

  /**
   * Sets the isFinal flag for an {@link ExerciseGrading}.
   */
  void setFinalGrading(long exerciseGradingId, boolean isFinal);
}
