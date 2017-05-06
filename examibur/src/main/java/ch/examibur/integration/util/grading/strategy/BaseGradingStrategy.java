package ch.examibur.integration.util.grading.strategy;

import ch.examibur.integration.utils.FormatUtil;

public class BaseGradingStrategy implements GradingStrategy {

  @Override
  public double calculateGrading(double totalPoints, double maxPoints) {
    double grading = (totalPoints / maxPoints) * 5 + 1;
    return FormatUtil.round(grading, FormatUtil.SCALE_NEAREST_HUNDRED);
  }

}
