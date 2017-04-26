package ch.examibur.business.exercise;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.NotFoundException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseService exerciseService = INJECTOR.getInstance(ExerciseService.class);

  @Test
  public void testGetMaxPoints() throws ExamiburException {
    double maxPoints = exerciseService.getMaxPoints(4);
    Assert.assertEquals(6, maxPoints, 0.01);
  }

  @Test
  public void testGetMaxPointsForExamWithoutExercises() throws ExamiburException {
    double maxPoints = exerciseService.getMaxPoints(1);
    Assert.assertEquals(0, maxPoints, 0.01);
  }

  @Test(expected = NotFoundException.class)
  public void testGetMaxPointsWithNonexistentExamId() throws ExamiburException {
    exerciseService.getMaxPoints(0);
  }

}
