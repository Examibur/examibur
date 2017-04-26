package ch.examibur.business.exercise;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exercise;
import java.util.List;
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

  @Test
  public void testGetExercises() {
    List<Exercise> exList = exerciseService.getExercises(3L);
    Assert.assertEquals(3, exList.size());
    for (Exercise ex : exList) {
      Assert.assertNotNull(ex);
    }
  }

  @Test
  public void testGetExercisesForExamWithoutExercises() {
    List<Exercise> exList = exerciseService.getExercises(1L);
    Assert.assertNotNull(exList);
    Assert.assertEquals(0, exList.size());
  }

  @Test
  public void testGetExercisesForNonExistingExamId() {
    List<Exercise> exList = exerciseService.getExercises(0L);
    Assert.assertNotNull(exList);
    Assert.assertEquals(0, exList.size());
  }

  @Test
  public void testGetExercisesForExamWithNegativeId() {
    List<Exercise> exList = exerciseService.getExercises(-42L);
    Assert.assertNotNull(exList);
    Assert.assertEquals(0, exList.size());
  }

}
