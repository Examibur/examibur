package ch.examibur.business.exercise;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseService exerciseService = INJECTOR.getInstance(ExerciseService.class);

  @Test
  public void testGetMaxPoints() {
    double maxPoints = exerciseService.getMaxPoints(4);
    Assert.assertEquals(6, maxPoints, 0.01);
  }

  @Test
  public void testGetMaxPointsForExamWithoutExercises() {
    double maxPoints = exerciseService.getMaxPoints(1);
    Assert.assertEquals(0, maxPoints, 0.01);
  }

  @Test
  public void testGetMaxPointsWithNonexistentExamId() {
    double maxPoints = exerciseService.getMaxPoints(0);
    Assert.assertEquals(0, maxPoints, 0.01);
  }

}
