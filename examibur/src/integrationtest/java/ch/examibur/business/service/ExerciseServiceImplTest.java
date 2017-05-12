package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.Exercise;
import ch.examibur.service.ExerciseService;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseServiceImplTest {

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

}
