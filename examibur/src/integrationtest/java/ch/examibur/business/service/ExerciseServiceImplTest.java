package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.Exercise;
import ch.examibur.service.ExerciseService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;

  @Rule
  public final DatabaseResource res = new DatabaseResource();
  private final ExerciseService exerciseService = IntegrationTestUtil.getInjector()
      .getInstance(ExerciseService.class);

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

  @Test
  public void testGetExercisesOrder() {
    List<Exercise> exList = exerciseService.getExercises(8L);
    List<Exercise> expectedList = new ArrayList<>(exList);
    Collections.sort(expectedList, (ex1, ex2) -> ex1.getOrderInExam() - ex2.getOrderInExam());
    Assert.assertEquals(expectedList, exList);
  }

  @Test
  public void testGetExercise() throws ExamiburException {
    Exercise exercise = exerciseService.getExercise(17L);
    Assert.assertNotNull(exercise);
    Assert.assertEquals(17L, exercise.getId());
    Assert.assertEquals(5D, exercise.getMaxPoints(), DOUBLE_DELTA);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExerciseWithNegativeId() throws ExamiburException {
    exerciseService.getExercise(-1L);
  }

}
