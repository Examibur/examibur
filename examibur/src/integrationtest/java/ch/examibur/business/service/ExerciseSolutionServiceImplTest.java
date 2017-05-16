package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
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
  public void testGetNextExerciseSolutionByExercise() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 48L);
    Assert.assertEquals(51L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByExerciseWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 54L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetNextExerciseSolutionByExerciseWithNonexistentId() throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 0);
  }

  @Test
  public void testGetNextExerciseSolutionByExercises() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISES, 52L);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 49L);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByParticipationWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 51L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetNextExerciseSolutionByParticipationWithNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 0);
  }

  @Test
  public void testGetNextExerciseSolutionByParticipations() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATIONS, 46L);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetNextExerciseSolutionWithInvalidEnumParameter() throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(null, 1L);
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
  public void testGetFirstExerciseSolutionByParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 17L);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipationWithFinishedParticipation()
      throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 16L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipationWithNonexistentId()
      throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipations() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATIONS, 16L);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByExercise() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 17L);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByExerciseWithNonexistentId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionByExercises() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getFirstExerciseSolution(BrowseSolutionsMode.BY_EXERCISES, 17L);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetFirstExerciseSolutionWithInvalidEnumParameter() throws ExamiburException {
    exerciseSolutionService.getFirstExerciseSolution(null, 1L);
  }
}
