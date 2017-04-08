package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExerciseGrading;
import java.util.List;

public interface ExerciseGradingDao {
  List<ExerciseGrading> getGradingsForExerciseSolution(long exerciseSolutionId);
}
