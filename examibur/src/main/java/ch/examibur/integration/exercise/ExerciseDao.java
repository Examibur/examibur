package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;

import java.util.List;

@FunctionalInterface
public interface ExerciseDao {

  public List<Exercise> getExercises(long examId);

}
