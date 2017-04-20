package ch.examibur.business.exercisegrading;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExerciseGradingServiceImplTest {
  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private final ExerciseGradingService exerciseGradingService = INJECTOR
      .getInstance(ExerciseGradingService.class);

  @Test
  public void testGetGradingForExerciseSolution()
      throws AuthorizationException, IOException, ParseException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(1L);

    Assert.assertNotNull(grading);
    Assert.assertNotNull(grading.getExerciseSolution());
    Assert.assertNotNull(grading.getGradingAuthor());

    Assert.assertEquals(grading.getId(), 1L);
    Assert.assertEquals(grading.getComment(), "Sehr gut gelöst.");
    Assert.assertEquals(grading.getCreatedInState(), ExamState.CORRECTION);
    Assert.assertEquals(grading.getcreationDate(),
        new Date(DATE_FORMAT.parse("2016-08-22").getTime()));
    Assert.assertEquals(grading.isFinalGrading(), true);
    Assert.assertEquals(grading.getPoints(), 5.0, 0.01);
    Assert.assertEquals(grading.getReasoning(), "");
    Assert.assertEquals(grading.getExerciseSolution().getId(), 1);
    Assert.assertEquals(grading.getGradingAuthor().getId(), 5);

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
