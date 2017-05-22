package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.User;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.NotFoundException;
import java.util.Optional;
import javax.persistence.EntityManager;

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
   * @see ch.examibur.integration.exercisegrading.ExerciseGradingDao#getTotalPointsOfExamGradings(long)
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
   * @see ch.examibur.integration.exercisegrading.ExerciseGradingDao#checkIfAllExercisesAreGraded(long,
   *      long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  boolean checkIfAllExercisesAreGraded(long examId, long participationId,
      EntityManager entityManager);

  /**
   * @param exerciseSolutionId
   *          the id of the {@link ExerciseSolution} which is graded
   * @param comment
   *          the comment which describes the solution
   * @param reasoning
   *          the reasoning which describes how much points were given
   * @param points
   *          the number of points given for the solution
   * @param gradingAuthor
   *          the author of this grading
   * @throws ExamiburException
   *           throws {@link IllegalOperationException} if the exam is not in CORRECTION nor in
   *           REVIEW. throws {@link IllegalOperationException} if there is already a grading for
   *           this {@link ExerciseSolution} in the same state.
   */
  void addGrading(long exerciseSolutionId, String comment, String reasoning, double points,
      User gradingAuthor) throws ExamiburException;

  /**
   * Sets the isFinal flag for an {@link ExerciseGrading}
   * 
   * @throws NotFoundException
   *           if the {@link ExerciseGrading} with the given id does not exist.
   */
  void setFinalGrading(long exerciseGradingId, boolean isFinal) throws NotFoundException;
}
