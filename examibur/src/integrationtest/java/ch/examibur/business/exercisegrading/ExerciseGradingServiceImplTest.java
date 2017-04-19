package ch.examibur.business.exercisegrading;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.AuthorizationException;
import ch.examibur.business.DatabaseResource;
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
  public void testGetGradingForExerciseSolution() throws AuthorizationException, IOException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(1L);
    Assert.assertNotNull(grading);
    Assert.assertNotNull(grading.getExerciseSolution());
    Assert.assertNotNull(grading.getGradingAuthor());
  }

  @Test
  public void testGetGradingForExerciseSolutionWithNonexistentId()
      throws AuthorizationException, IOException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(100L);
    Assert.assertNull(grading);
  }

  @Test
  public void testGetGradingForExerciseSolutionForSolutionWithoutGrading()
      throws AuthorizationException, IOException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(50L);
    Assert.assertNull(grading);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetGradingForExerciseSolutionWithNegativeId()
      throws AuthorizationException, IOException {
    exerciseGradingService.getGradingForExerciseSolution(-1L);
    Assert.fail();
  }

  @Test
  public void testGetReviewForExerciseSolution() throws AuthorizationException, IOException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(2L);
    Assert.assertNotNull(review);
    Assert.assertNotNull(review.getExerciseSolution());
    Assert.assertNotNull(review.getGradingAuthor());
  }

  @Test
  public void testGetReviewForExerciseSolutionWithNonexistendId()
      throws AuthorizationException, IOException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(200L);
    Assert.assertNull(review);
  }

  @Test
  public void testGetReviewForExerciseSolutionForSolutionWithoutReview()
      throws AuthorizationException, IOException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(20L);
    Assert.assertNull(review);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetReviewForExerciseSolutionWithNegativeId()
      throws AuthorizationException, IOException {
    exerciseGradingService.getReviewForExerciseSolution(-1L);
  }

}
