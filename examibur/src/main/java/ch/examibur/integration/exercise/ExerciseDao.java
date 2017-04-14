package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;
import java.util.List;

public interface ExerciseDao {

  public double getMaxPoints(long examId);

  public List<Exercise> getExercises(long examId);

}
