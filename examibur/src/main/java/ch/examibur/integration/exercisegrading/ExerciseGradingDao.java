package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;

public interface ExerciseGradingDao {
  
  /**
   * @param exerciseSolutionId
   *          the id of the exerciseSolution to get the grading for
   * @param state
   *          the ExamState the ExerciseSolution was created in, e.g. REVIEW or CORRECTION
   * @return the ExerciseGrading if it was found, null otherwise.
   */
  public ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state);
  
  /**
   * @param examId
   *          the id of the exam to get the total points of all gradings
   * @param participationId
   *          the id of the participation to get the total points for a specific participant
   * @return the total points of a all exam gradings for a specific participant.
   */
  public double getTotalPointsOfExamGradings(long examId, long participationId);

  /**
   * @param examId
   *          the id of the exam to get the progress of an exam grading
   * @param participationId
   *          the id of the participation to get the progress for a specific participant
   * @return the progress of a all exam gradings for a specific participant.
   */
  public double getProgressOfExamGradings(long examId, long participationId);
  
}
