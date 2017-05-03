package ch.examibur.business.exercisegrading;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.ExerciseGrading;
import java.io.IOException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseGradingServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();
  private final ExerciseGradingService exerciseGradingService = INJECTOR
      .getInstance(ExerciseGradingService.class);

  @Test
  public void testAddCorrection() throws NotFoundException, AuthorizationException, IOException {
    long exerciseSolutionId = 54L;
    exerciseGradingService.addGrading(exerciseSolutionId, "random comment", "random reasoning", 1D);
    ExerciseGrading exerciseGrading = exerciseGradingService
        .getGradingForExerciseSolution(exerciseSolutionId);
    Assert.assertEquals(exerciseGrading.isFinalGrading(), true);
  }

  @Test
  public void testAddReview() throws NotFoundException, AuthorizationException, IOException {
    long exerciseSolutionId = 35L;
    exerciseGradingService.addGrading(exerciseSolutionId, "random comment", "random reasoning", 1D);
    ExerciseGrading exerciseReview = exerciseGradingService
        .getReviewForExerciseSolution(exerciseSolutionId);
    ExerciseGrading exerciseGrading = exerciseGradingService
        .getGradingForExerciseSolution(exerciseSolutionId);
    Assert.assertEquals(exerciseReview.isFinalGrading(), true);
    Assert.assertEquals(exerciseGrading.isFinalGrading(), false);
  }

  @Test(expected = NotFoundException.class)
  public void testAddGradingWithNonexistentSolutionId()
      throws NotFoundException, AuthorizationException, IOException {
    exerciseGradingService.addGrading(0, "random comment", "random reasoning", 1);
  }
}
