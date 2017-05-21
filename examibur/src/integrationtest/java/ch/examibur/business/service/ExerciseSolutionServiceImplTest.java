package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExerciseParticipantOverview;
import ch.examibur.service.model.ExerciseSolutionOverview;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseSolutionServiceImplTest {

  private static final double DOUBLE_DELTA = 0.0001;

  @Rule
  public final DatabaseResource res = new DatabaseResource();
  private final ExerciseSolutionService exerciseSolutionService =
      IntegrationTestUtil.getInjector().getInstance(ExerciseSolutionService.class);

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
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 8L, 16L, 48L);
    Assert.assertEquals(51L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByExerciseWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 8L, 18L, 54L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetNextExerciseSolutionByExerciseWithNonexistentId() throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 8L, 16L,
        2000L);
  }

  @Test
  public void testGetNextExerciseSolutionByExercises() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISES, 8L, 18L, 52L);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 8L, 17L, 49L);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetNextExerciseSolutionByParticipationWithLastId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 8L, 17L, 51L);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test(expected = NotFoundException.class)
  public void testGetNextExerciseSolutionByParticipationWithNonexistentId()
      throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 8L, 17L,
        2000L);
  }

  @Test
  public void testGetNextExerciseSolutionByParticipations() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATIONS, 8L, 16L, 46L);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetNextExerciseSolutionWithInvalidEnumParameter() throws ExamiburException {
    exerciseSolutionService.getNextExerciseSolution(null, 8L, 16L, 46L);
  }

  @Test
  public void testGetExerciseSolutionsForExamParticipation() throws ExamiburException {
    List<ExerciseSolutionOverview> overviews =
        exerciseSolutionService.getExerciseSolutionsForExamParticipation(13L);

    Assert.assertNotNull(overviews);
    Assert.assertEquals(3, overviews.size());

    Assert.assertEquals(3.0, overviews.get(0).getPoints().get(), 0.01); // ExerciseSolutionId: 37
    Assert.assertEquals(5.0, overviews.get(1).getPoints().get(), 0.01); // ExerciseSolutionId: 38
    Assert.assertEquals(0.0, overviews.get(2).getPoints().get(), 0.01); // ExerciseSolutionId: 39
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
    List<ExerciseSolutionOverview> overviews =
        exerciseSolutionService.getExerciseSolutionsForExamParticipation(17L);
    List<ExerciseSolutionOverview> expectedList = new ArrayList<>(overviews);
    Collections.sort(expectedList,
        (ov1, ov2) -> ov1.getExerciseSolution().getExercise().getOrderInExam()
            - ov2.getExerciseSolution().getExercise().getOrderInExam());
    Assert.assertEquals(expectedList, overviews);
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipation() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 8L, 17L, 0);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipationWithFinishedParticipation()
      throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 8L, 16L, 0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipationWithNonexistentId()
      throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATION, 0, 0, 0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test
  public void testGetFirstExerciseSolutionByParticipations() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_PARTICIPATIONS, 8L, 16L, 0);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByExercise() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 8L, 17L, 0);
    Assert.assertEquals(50L, nextExerciseSolution.getId());
  }

  @Test
  public void testGetFirstExerciseSolutionByExerciseWithNonexistentId() throws ExamiburException {
    ExerciseSolution nextExerciseSolution =
        exerciseSolutionService.getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISE, 0, 0, 0);
    Assert.assertNull(nextExerciseSolution);
  }

  @Test

  public void testGetFirstExerciseSolutionByExercises() throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(BrowseSolutionsMode.BY_EXERCISES, 8L, 17L, 0);
    Assert.assertEquals(49L, nextExerciseSolution.getId());
  }

  public void testGetExerciseParticipantsOverview() throws ExamiburException {
    List<ExerciseParticipantOverview> exerciseParticipantsOverview =
        exerciseSolutionService.getExerciseParticipantsOverview(8);
    Assert.assertEquals(3, exerciseParticipantsOverview.size());

    testExerciseParticipantOverview(exerciseParticipantsOverview.get(0), 9D, Optional.of(5.5D),
        false); // Anonymer Löwe
    testExerciseParticipantOverview(exerciseParticipantsOverview.get(1), 6D, Optional.of(4D),
        false); // Anonymes Lama
    testExerciseParticipantOverview(exerciseParticipantsOverview.get(2), 8D, Optional.of(5D),
        false); // Anonymes Pony
  }

  @Test
  public void testGetExerciseParticipantsOverviewMissingGradings() throws ExamiburException {
    List<ExerciseParticipantOverview> exerciseParticipantsOverview =
        exerciseSolutionService.getExerciseParticipantsOverview(17);
    Assert.assertEquals(3, exerciseParticipantsOverview.size());

    testExerciseParticipantOverview(exerciseParticipantsOverview.get(0), 5D, Optional.of(2.67D),
        true); // Anonymer Panda
    testExerciseParticipantOverview(exerciseParticipantsOverview.get(1), 0D, Optional.empty(),
        false); // Anonymer Elefant
    testExerciseParticipantOverview(exerciseParticipantsOverview.get(2), 2D, Optional.empty(),
        false); // Anonymer Flamingo
  }

  private void testExerciseParticipantOverview(
      ExerciseParticipantOverview exerciseParticipantOverview, double expectedTotalPoints,
      Optional<Double> expectedGrading, boolean expectedIsDone) {
    Assert.assertEquals(expectedTotalPoints, exerciseParticipantOverview.getTotalPoints(),
        DOUBLE_DELTA);
    if (expectedGrading.isPresent()) {
      Assert.assertEquals(expectedGrading.get(), exerciseParticipantOverview.getGrading().get(),
          DOUBLE_DELTA);
    } else {
      Assert.assertEquals(expectedGrading, exerciseParticipantOverview.getGrading());
    }
    Assert.assertEquals(expectedIsDone, exerciseParticipantOverview.isDone());
  }
}
