package ch.examibur.business.service;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseSolutionServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseSolutionService exerciseSolutionService = INJECTOR
      .getInstance(ExerciseSolutionService.class);

  @Test
  public void testGetExerciseSolution() throws ExamiburException {
    ExerciseSolution exerciseSolution = exerciseSolutionService.getExerciseSolution(1);
    Assert.assertEquals(exerciseSolution.getId(), 1L);
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
