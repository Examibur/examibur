package ch.examibur.business.exercise;

import ch.examibur.domain.Exercise;
import java.util.List;

public interface ExerciseService {
  
  public double getMaxPoints(long examId);

  public List<Exercise> getExercises(long examId); 

}
