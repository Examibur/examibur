package ch.examibur.business.util.grading.strategy;

@FunctionalInterface
public interface GradingStrategy {
  
  public double calculateGrading(double totalPoints, double maxPoints);
  
}
