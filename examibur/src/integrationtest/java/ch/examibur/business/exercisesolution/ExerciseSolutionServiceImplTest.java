package ch.examibur.business.exercisesolution;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
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
    ExerciseSolution exerciseSolution = exerciseSolutionService.getExerciseSolution(1);
    Assert.assertEquals(exerciseSolution.getId(), 1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionWithNonexistentId()
      throws NotFoundException, AuthorizationException, IOException {
    exerciseSolutionService.getExerciseSolution(0);
    Assert.fail();
  }

  @Test
  public void testGetExerciseSolutionFromNextParticipation()
      throws NotFoundException, AuthorizationException, IOException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getExerciseSolutionFromNextParticipation(48);
    Assert.assertEquals(nextExerciseSolution.getId(), 51L);
  }

  @Test
  public void testGetExerciseSolutionFromNextParticipationWithLastId()
      throws NotFoundException, AuthorizationException, IOException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getExerciseSolutionFromNextParticipation(54);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionFromNextParticipationWithNonexistentId()
      throws NotFoundException, AuthorizationException, IOException {
    exerciseSolutionService.getExerciseSolutionFromNextParticipation(0);
    Assert.fail();
  }

}
