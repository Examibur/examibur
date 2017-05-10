package ch.examibur.service.model;

import ch.examibur.domain.ExerciseSolution;
import java.util.Optional;

public class ExerciseSolutionOverview {
  private ExerciseSolution exerciseSolution;
  private Optional<Double> points;

  public ExerciseSolutionOverview(ExerciseSolution exerciseSolution, Optional<Double> points) {
    this.exerciseSolution = exerciseSolution;
    this.setPoints(points);
  }

  public ExerciseSolution getExerciseSolution() {
    return exerciseSolution;
  }

  public void setExerciseSolution(ExerciseSolution exerciseSolution) {
    this.exerciseSolution = exerciseSolution;
  }

  public Optional<Double> getPoints() {
    return points;
  }

  public void setPoints(Optional<Double> points) {
    this.points = points;
  }

}
