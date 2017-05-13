package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExerciseSolutionOverview;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseSolutionServiceImplTest {

  @Rule
  public final DatabaseResource res = new DatabaseResource();
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
        .getExerciseSolutionFromNextParticipation(48L);
    Assert.assertEquals(nextExerciseSolution.getId(), 51L);
  }

  @Test
  public void testGetExerciseSolutionFromNextParticipationWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getExerciseSolutionFromNextParticipation(54L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionFromNextParticipationWithNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getExerciseSolutionFromNextParticipation(0);
  }

  @Test
  public void testGetNextExerciseSolutionFromParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolutionFromParticipation(49L);
    Assert.assertEquals(nextExerciseSolution.getId(), 50L);
  }

  @Test
  public void testGetNextExerciseSolutionFromParticipationWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolutionFromParticipation(51L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetNextExerciseSolutionFromParticipationWithNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolutionFromParticipation(0);
  }

  @Test
  public void testGetExerciseSolutionsForExamParticipation() throws ExamiburException {
    List<ExerciseSolutionOverview> overviews = exerciseSolutionService
        .getExerciseSolutionsForExamParticipation(13L);

    Assert.assertNotNull(overviews);
    Assert.assertEquals(3, overviews.size());

    for (ExerciseSolutionOverview overview : overviews) {
      Assert.assertNotNull(overview.getExerciseSolution());
      if (overview.getExerciseSolution().getId() == 37L) {
        Assert.assertEquals(3.0, overview.getPoints().get(), 0.01);
      } else if (overview.getExerciseSolution().getId() == 38L) {
        Assert.assertEquals(5.0, overview.getPoints().get(), 0.01);
      } else if (overview.getExerciseSolution().getId() == 39L) {
        Assert.assertEquals(0.0, overview.getPoints().get(), 0.01);
      }
    }
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExerciseSolutionsForExamParticipationWithNegativeId()
      throws ExamiburException {
    exerciseSolutionService.getExerciseSolutionsForExamParticipation(-1L);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExerciseSolutionsForExamParticipationForNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getExerciseSolutionsForExamParticipation(2000L);
  }

  @Test
  public void testGetExerciseSolutionsForExamParticipationOrderedInExam() throws ExamiburException {
    List<ExerciseSolutionOverview> overviews = exerciseSolutionService
        .getExerciseSolutionsForExamParticipation(17L);
    List<ExerciseSolutionOverview> expectedList = new ArrayList<>(overviews);
    Collections.sort(expectedList,
        (ov1, ov2) -> ov1.getExerciseSolution().getExercise().getOrderInExam()
            - ov2.getExerciseSolution().getExercise().getOrderInExam());
    Assert.assertEquals(expectedList, overviews);
  }

  @Test
  public void testGetFirstExerciseSolutionFromParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolutionFromParticipation(17L);
    Assert.assertEquals(nextExerciseSolution.getId(), 49L);
  }

  @Test
  public void testGetFirstExerciseSolutionFromParticipationWithNonexistentId()
      throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolutionFromParticipation(0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionFromExercise() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolutionFromExercise(17L);
    Assert.assertEquals(nextExerciseSolution.getId(), 50L);
  }

  @Test
  public void testGetFirstExerciseSolutionFromExerciseWithNonexistentId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolutionFromExercise(0);
    Assert.assertNull(nextExerciseSolution);
  }
}
