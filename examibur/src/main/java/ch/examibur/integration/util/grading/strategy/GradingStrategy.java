package ch.examibur.integration.util.grading.strategy;

@FunctionalInterface
public interface GradingStrategy {
  
  public double calculateGrading(double totalPoints, double maxPoints);
  
}
