package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseSolutionServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseSolutionService exerciseSolutionService = IntegrationTestUtil.getInjector()
      .getInstance(ExerciseSolutionService.class);

  @Test
  public void testGetExerciseSolution() throws ExamiburException {
    ExerciseSolution exerciseSolution = exerciseSolutionService.getExerciseSolution(1L);

    Assert.assertNotNull(exerciseSolution);
    Assert.assertNotNull(exerciseSolution.getParticipation());
    Assert.assertNotNull(exerciseSolution.getParticipantSolution());
    Assert.assertNotNull(exerciseSolution.getExercise());

    Assert.assertEquals(1L, exerciseSolution.getId());
    Assert.assertEquals(true, exerciseSolution.isDone());
    Assert.assertEquals(1L, exerciseSolution.getExercise().getId());
    Assert.assertEquals(1L, exerciseSolution.getParticipation().getId());
    Assert.assertEquals(19L, exerciseSolution.getParticipantSolution().getId());

  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExerciseSolutionWithNegativeId() throws ExamiburException {
    exerciseSolutionService.getExerciseSolution(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionWithNonexistentId() throws ExamiburException {
    exerciseSolutionService.getExerciseSolution(0);
  }

  @Test
  public void testGetExerciseSolutionFromNextParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getExerciseSolutionFromNextParticipation(48);
    Assert.assertEquals(nextExerciseSolution.getId(), 51L);
  }

  @Test
  public void testGetExerciseSolutionFromNextParticipationWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getExerciseSolutionFromNextParticipation(54);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionFromNextParticipationWithNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getExerciseSolutionFromNextParticipation(0);
  }
}
