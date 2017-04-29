package ch.examibur.business.util.grading;

import ch.examibur.business.util.grading.strategy.GradingStrategy;

public final class GradingUtil {
  
  private GradingUtil(){
  }
  
  /**
   * Calculates the grading based on total points, max points and the provided grading strategy.
   * 
   * @param gradingStrategy the grading strategy
   * @param totalPoints total points
   * @param maxPoints max points
   * @return exam grading
   */
  public static double calculateGrading(GradingStrategy gradingStrategy, double totalPoints, double maxPoints) {
    return gradingStrategy.calculateGrading(totalPoints, maxPoints);
  } 

}
