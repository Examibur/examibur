package ch.examibur.integration.util.grading;

import ch.examibur.integration.util.grading.strategy.BaseGradingStrategy;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class GradingUtilTest {
  
  private static final double DOUBLE_DELTA = 0.0001;

  @Test
  public void testCalculateGradingWithBaseGradingStrategy() {
    double grade = GradingUtil.calculateGrading(new BaseGradingStrategy(), 5, 10);
    Assert.assertEquals(3.5D, grade, DOUBLE_DELTA);
  }

  @Test
  public void testCalculateAverageGrade() {
    List<Double> gradings = new ArrayList<>();
    gradings.add(4D);
    gradings.add(3D);
    gradings.add(5D);
    Assert.assertEquals(4D, GradingUtil.calculateAverageGrade(gradings), DOUBLE_DELTA);
  }

  @Test
  public void testCalculateMedianGradeOdd() {
    List<Double> gradings = new ArrayList<>();
    gradings.add(4D);
    gradings.add(3D);
    gradings.add(5D);
    Assert.assertEquals(4D, GradingUtil.calculateMedianGrade(gradings), DOUBLE_DELTA);
  }
  
  @Test
  public void testCalculateMedianGradeEven() {
    List<Double> gradings = new ArrayList<>();
    gradings.add(3.5D);
    gradings.add(4D);
    gradings.add(3D);
    gradings.add(5D);
    Assert.assertEquals(3.75D, GradingUtil.calculateMedianGrade(gradings), DOUBLE_DELTA);
  }
  
  @Test
  public void testCalculateMedianGradeEvenTwo() {
    List<Double> gradings = new ArrayList<>();
    gradings.add(3D);
    gradings.add(4D);
    gradings.add(3D);
    gradings.add(5D);
    Assert.assertEquals(3.5D, GradingUtil.calculateMedianGrade(gradings), DOUBLE_DELTA);
  }

}
