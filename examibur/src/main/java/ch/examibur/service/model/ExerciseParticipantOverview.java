package ch.examibur.service.model;

import ch.examibur.domain.ExerciseSolution;
import java.util.Optional;

public final class ExerciseParticipantOverview {

  private ExerciseSolution exerciseSolution;
  private double totalPoints;
  private Optional<Double> grading;

  public ExerciseSolution getExerciseSolution() {
    return exerciseSolution;
  }

  public void setExerciseSolution(ExerciseSolution exerciseSolution) {
    this.exerciseSolution = exerciseSolution;
  }

  public double getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(double totalPoints) {
    this.totalPoints = totalPoints;
  }

  public Optional<Double> getGrading() {
    return grading;
  }

  public void setGrading(Optional<Double> grading) {
    this.grading = grading;
  }

}
