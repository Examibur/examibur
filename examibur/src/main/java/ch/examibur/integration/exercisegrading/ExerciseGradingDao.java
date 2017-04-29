package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;

public interface ExerciseGradingDao {
  
  /**
   * @param exerciseSolutionId
   *          the id of the exerciseSolution to get the grading for. The exerciseSolution with the
   *          given id must exist, otherwise <code>null</code> is returned.
   * @param state
   *          the ExamState the ExerciseSolution was created in, e.g. REVIEW or CORRECTION
   * @return the ExerciseGrading if it was found, null otherwise.
   */
  public ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state);
  
  /**
   * @param examParticipationId
   *          the id of the participation to get the total points for a specific participant
   * @return the total points of a all exam gradings for a specific participant.
   */
  public double getTotalPointsOfExamGradings(long examParticipationId);

  /**
   * @param examParticipationId
   *          the id of the participation to get the progress for a specific participant
   * @return the progress of a all exam gradings for a specific participant.
   */
  public double getProgressOfExamGradings(long examParticipationId);
  
  /**
   * @param exerciseId
   *          the id of the exercise to get all corresponding exercise gradings        
   * @return the average points of all exercises gradings for one exercise.
   */
  public double getAveragePointsOfExercise(long exerciseId);
  
}
