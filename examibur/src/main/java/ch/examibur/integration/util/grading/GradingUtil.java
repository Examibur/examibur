package ch.examibur.integration.util.grading;

import ch.examibur.integration.util.grading.strategy.GradingStrategy;
import ch.examibur.integration.utils.FormatUtil;
import java.util.Collections;
import java.util.List;

public final class GradingUtil {
  
  private GradingUtil() {
  }

  /**
   * Calculates the grading based on total points, max points and the provided grading strategy.
   * 
   * @param gradingStrategy
   *          the grading strategy
   * @param totalPoints
   *          total points
   * @param maxPoints
   *          max points
   * @return exam grading
   */
  public static double calculateGrading(GradingStrategy gradingStrategy, double totalPoints,
      double maxPoints) {
    return gradingStrategy.calculateGrading(totalPoints, maxPoints);
  }

  /**
   * Calculates the average grade of all provided gradings.
   * 
   * @param gradings
   *          a list of gradings
   * @return the average grade
   */
  public static double calculateAverageGrade(List<Double> gradings) {
    double totalOfGradings = gradings.stream().mapToDouble(Double::doubleValue).sum();
    double averageGrade = totalOfGradings / gradings.size();
    return FormatUtil.round(averageGrade, FormatUtil.SCALE_NEAREST_HUNDRED);
  }

  /**
   * Calculates the median of all provided gradings.
   * 
   * @param gradings
   *          a list of gradings
   * @return the median
   */
  public static double calculateMedianGrade(List<Double> gradings) {
    Collections.sort(gradings);
    int size = gradings.size();
    double median;
    if (size % 2 == 0) {
      median = (gradings.get(size / 2) + gradings.get(size / 2 - 1)) / 2;
    } else {
      median = gradings.get(size / 2);
    }
    return FormatUtil.round(median, FormatUtil.SCALE_NEAREST_HUNDRED);
  }

}
