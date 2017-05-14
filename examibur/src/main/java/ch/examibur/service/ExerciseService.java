package ch.examibur.service;

import ch.examibur.domain.Exercise;
import java.util.List;

public interface ExerciseService {

  /**
   * @param examId
   *          the id of the exam. This exam must exist!
   * @return a list of all exercises for the given exam.
   */
  List<Exercise> getExercises(long examId);
  
  /**
   * @param exerciseId
   *          the id of the exercise
   * @return the exercise for the specified exercise id.
   */
  Exercise getExercise(long exerciseId);

}
