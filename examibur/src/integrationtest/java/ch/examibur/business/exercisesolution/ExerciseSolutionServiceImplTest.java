package ch.examibur.business.exercisesolution;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
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
  private final ExerciseSolutionService exerciseSolutionService = IntegrationTestUtil.getInjector()
      .getInstance(ExerciseSolutionService.class);

  @Test
  public void testGetExerciseSolution()
      throws NotFoundException, AuthorizationException, IOException {
    ExerciseSolution exerciseSolution = exerciseSolutionService.getExerciseSolution(1L);

    Assert.assertNotNull(exerciseSolution);
    Assert.assertNotNull(exerciseSolution.getParticipation());
    Assert.assertNotNull(exerciseSolution.getParticipantSolution());
    Assert.assertNotNull(exerciseSolution.getExercise());

    Assert.assertEquals(exerciseSolution.getId(), 1L);
    Assert.assertEquals(exerciseSolution.isDone(), true);
    Assert.assertEquals(exerciseSolution.getExercise().getId(), 1);
    Assert.assertEquals(exerciseSolution.getParticipation().getId(), 1);
    Assert.assertEquals(exerciseSolution.getParticipantSolution().getId(), 19);

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
