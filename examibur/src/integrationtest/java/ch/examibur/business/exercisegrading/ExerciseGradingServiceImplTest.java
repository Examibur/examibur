package ch.examibur.business.exercisegrading;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
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
  public void testGetGradingForExerciseSolution() throws ParseException, ExamiburException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(1L);

    Assert.assertNotNull(grading);
    Assert.assertNotNull(grading.getExerciseSolution());
    Assert.assertNotNull(grading.getGradingAuthor());

    Assert.assertEquals(1L, grading.getId());
    Assert.assertEquals("Sehr gut gelöst.", grading.getComment());
    Assert.assertEquals(ExamState.CORRECTION, grading.getCreatedInState());
    Assert.assertEquals(new Date(DATE_FORMAT.parse("2016-08-22").getTime()),
        grading.getcreationDate());
    Assert.assertEquals(true, grading.isFinalGrading());
    Assert.assertEquals(5.0, grading.getPoints(), 0.01);
    Assert.assertEquals("", grading.getReasoning());
    Assert.assertEquals(1L, grading.getExerciseSolution().getId());
    Assert.assertEquals(5L, grading.getGradingAuthor().getId());

  }

  @Test
  public void testGetGradingForExerciseSolutionWithNonexistentId() throws ExamiburException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(100L);
    Assert.assertNull(grading);
  }

  @Test
  public void testGetGradingForExerciseSolutionForSolutionWithoutGrading()
      throws ExamiburException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(50L);
    Assert.assertNull(grading);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetGradingForExerciseSolutionWithNegativeId() throws ExamiburException {
    exerciseGradingService.getGradingForExerciseSolution(-1L);
  }

  @Test
  public void testGetReviewForExerciseSolution() throws ExamiburException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(2L);
    Assert.assertNotNull(review);
    Assert.assertNotNull(review.getExerciseSolution());
    Assert.assertNotNull(review.getGradingAuthor());
  }

  @Test
  public void testGetReviewForExerciseSolutionWithNonexistendId() throws ExamiburException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(200L);
    Assert.assertNull(review);
  }

  @Test
  public void testGetReviewForExerciseSolutionForSolutionWithoutReview() throws ExamiburException {
    ExerciseGrading review = exerciseGradingService.getReviewForExerciseSolution(20L);
    Assert.assertNull(review);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetReviewForExerciseSolutionWithNegativeId() throws ExamiburException {
    exerciseGradingService.getReviewForExerciseSolution(-1L);
  }

}
