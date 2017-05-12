package ch.examibur.integration.util.grading.strategy;

@FunctionalInterface
public interface GradingStrategy {

  double calculateGrading(double totalPoints, double maxPoints);

}
