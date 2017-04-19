package ch.examibur.business.exercisesolution;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.AuthorizationException;
import ch.examibur.business.DatabaseResource;
import ch.examibur.business.NotFoundException;
import ch.examibur.domain.ExerciseSolution;
import java.io.IOException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseSolutionServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseSolutionService exerciseSolutionService = INJECTOR
      .getInstance(ExerciseSolutionService.class);

  @Test
  public void testGetExerciseSolution()
      throws NotFoundException, AuthorizationException, IOException {
    ExerciseSolution exerciseSolution = exerciseSolutionService.getExerciseSolution(1L);

    Assert.assertNotNull(exerciseSolution);
    Assert.assertNotNull(exerciseSolution.getParticipation());
    Assert.assertNotNull(exerciseSolution.getExercise());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetExerciseSolutionWithNegativeId()
      throws NotFoundException, AuthorizationException, IOException {
    exerciseSolutionService.getExerciseSolution(-1L);
    Assert.fail();
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionWithNonexistentId()
      throws NotFoundException, AuthorizationException, IOException {
    exerciseSolutionService.getExerciseSolution(1000L);
    Assert.fail();
  }
}
