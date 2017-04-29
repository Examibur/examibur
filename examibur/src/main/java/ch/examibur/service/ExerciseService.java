package ch.examibur.service;

import ch.examibur.domain.Exercise;
import java.util.List;

@FunctionalInterface
public interface ExerciseService {

  /**
   * @param examId
   *          the id of the exam. This exam must exist!
   * @return a list of all exercises for the given exam.
   */
  public List<Exercise> getExercises(long examId);

}
