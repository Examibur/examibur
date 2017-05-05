package ch.examibur.integration.util.grading.strategy;

public class BaseGradingStrategy implements GradingStrategy {

  @Override
  public double calculateGrading(double totalPoints, double maxPoints) {
    double grading = (totalPoints / maxPoints) * 5 + 1;
    return Math.round(grading * 100) / 100d;
  }

}
