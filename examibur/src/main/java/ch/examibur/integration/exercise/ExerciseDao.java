package ch.examibur.integration.exercise;

import ch.examibur.domain.Exam;
import ch.examibur.domain.Exercise;

import java.util.List;

public interface ExerciseDao {

  /**
   * @param examId
   *          the id of the exam. If the exam is not
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   */
  public double getMaxPoints(long examId);

  public List<Exercise> getExercises(long examId);

}
