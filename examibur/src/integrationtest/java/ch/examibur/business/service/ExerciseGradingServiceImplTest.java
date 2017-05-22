package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.business.util.AuthenticationUtil;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.AuthenticationService;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.exception.ValidationException;
import ch.examibur.service.model.AuthenticationInformation;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExerciseGradingServiceImplTest {
  @Rule
  public final DatabaseResource res = new DatabaseResource();

  private static final String USER_MAXIMILIAN_MUELLER = "maximilian.mueller";
  private static final String USER_UWE_BAER = "uwe.baer";
  private static final String USER_JUERGEN_KOENIG = "juergen.koenig";

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private final ExerciseGradingService exerciseGradingService = IntegrationTestUtil.getInjector()
      .getInstance(ExerciseGradingService.class);
  private final ExerciseSolutionService exerciseSolutionService = IntegrationTestUtil.getInjector()
      .getInstance(ExerciseSolutionService.class);
  private final AuthenticationService authenticationService = IntegrationTestUtil.getInjector()
      .getInstance(AuthenticationService.class);

  @Test
  public void testGetGradingForExerciseSolution() throws ParseException, ExamiburException {
    ExerciseGrading grading = exerciseGradingService.getGradingForExerciseSolution(1L);

    Assert.assertNotNull(grading);
    Assert.assertNotNull(grading.getExerciseSolution());
    Assert.assertNotNull(grading.getGradingAuthor());

    Assert.assertEquals(1L, grading.getId());
    Assert.assertEquals("Sehr gut gelöst.", grading.getComment());
    Assert.assertEquals(ExamState.CORRECTION, grading.getCreatedInState());
    Assert.assertEquals(new Date(dateFormat.parse("2016-08-22").getTime()),
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

  @Test
  public void testAddCorrection() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    long exerciseSolutionId = 54L;
    String comment = "random comment";
    String reasoning = "random reasoning";
    Double points = 1D;

    exerciseGradingService.addGrading(exerciseSolutionId, comment, reasoning, points);
    ExerciseGrading exerciseGrading = exerciseGradingService
        .getGradingForExerciseSolution(exerciseSolutionId);
    ExerciseSolution exerciseSolution = exerciseSolutionService
        .getExerciseSolution(exerciseSolutionId);

    Assert.assertEquals(comment, exerciseGrading.getComment());
    Assert.assertEquals(reasoning, exerciseGrading.getReasoning());
    Assert.assertEquals(points, exerciseGrading.getPoints(), 0.01);
    Assert.assertEquals(true, exerciseSolution.isDone());
  }

  @Test
  public void testAddReview() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    long exerciseSolutionId = 35L;
    String comment = "random comment";
    String reasoning = "random reasoning";
    Double points = 1D;

    exerciseGradingService.addGrading(exerciseSolutionId, comment, reasoning, points);
    ExerciseGrading exerciseReview = exerciseGradingService
        .getReviewForExerciseSolution(exerciseSolutionId);
    ExerciseSolution exerciseSolution = exerciseSolutionService
        .getExerciseSolution(exerciseSolutionId);

    Assert.assertEquals(comment, exerciseReview.getComment());
    Assert.assertEquals(reasoning, exerciseReview.getReasoning());
    Assert.assertEquals(points, exerciseReview.getPoints(), 0.01);
    Assert.assertEquals(true, exerciseSolution.isDone());
  }

  @Test(expected = NotFoundException.class)
  public void testAddGradingWithNonexistentSolutionId() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(0L, "random comment", "random reasoning", 1D);
  }

  @Test(expected = InvalidParameterException.class)
  public void testAddGradingWithNegativeSolutionId() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(-1L, "random comment", "random reasoning", 1D);
  }

  @Test(expected = IllegalOperationException.class)
  public void testAddGradingInInvalidExamState() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(18L, "random comment", "random reasoning", 1D);
  }

  @Test(expected = IllegalOperationException.class)
  public void testAddSecondCorrection() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(54L, "random comment", "random reasoning", 1D);
    exerciseGradingService.addGrading(54L, "second comment", "second reasoning", 2D);
  }

  @Test(expected = IllegalOperationException.class)
  public void testAddSecondReview() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(35L, "random comment", "random reasoning", 1D);
    exerciseGradingService.addGrading(35L, "second comment", "second reasoning", 2D);
  }

  @Test(expected = AuthorizationException.class)
  public void testAddGradingWithoutAuthorLoggedIn() throws ExamiburException {
    exerciseGradingService.addGrading(54L, "random comment", "random reasoning", 1D);
  }

  @Test(expected = ValidationException.class)
  public void testAddGradingWithMoreThanMaxPoints() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(51L, "random comment", "random reasoning", 10D);
  }

  @Test(expected = ValidationException.class)
  public void testAddGradingWithLessThanZeroPoints() throws ExamiburException {
    fakeLogin(USER_MAXIMILIAN_MUELLER);
    exerciseGradingService.addGrading(51L, "random comment", "random reasoning", -1D);
  }

  @Test
  public void testAcceptGrading() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    final long exerciseSolutionId = 10L;
    exerciseGradingService.approveReview(exerciseSolutionId, 53L, "accept");
    ExerciseGrading review =
        exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId);
    ExerciseGrading grading =
        exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId);
    ExerciseSolution solution = exerciseSolutionService.getExerciseSolution(exerciseSolutionId);

    Assert.assertNotNull(review);
    Assert.assertNotNull(solution);
    Assert.assertTrue(solution.isDone());
    Assert.assertTrue(review.isFinalGrading());
    Assert.assertFalse(grading.isFinalGrading());

  }

  @Test
  public void testRejectGrading() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    final long exerciseSolutionId = 10L;
    exerciseGradingService.approveReview(exerciseSolutionId, 53L, "reject");
    ExerciseGrading review =
        exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId);
    ExerciseGrading grading =
        exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId);
    ExerciseSolution solution = exerciseSolutionService.getExerciseSolution(exerciseSolutionId);

    Assert.assertNotNull(review);
    Assert.assertNotNull(solution);
    Assert.assertTrue(solution.isDone());
    Assert.assertTrue(grading.isFinalGrading());
    Assert.assertFalse(review.isFinalGrading());
  }

  @Test(expected = ValidationException.class)
  public void testApproveReviewWithWrongParameter() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    exerciseGradingService.approveReview(10L, 53L, "somestring");
  }

  @Test(expected = InvalidParameterException.class)
  public void testApproveReviewWithNegativeIds() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    exerciseGradingService.approveReview(-1L, -1L, "accept");
  }

  @Test(expected = IllegalOperationException.class)
  public void testApproveReviewWithWrongSolution() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    exerciseGradingService.approveReview(9L, 53L, "accept");
  }

  @Test(expected = IllegalOperationException.class)
  public void testApproveReviewWithWrongGrading() throws ExamiburException {
    fakeLogin(USER_UWE_BAER);
    exerciseGradingService.approveReview(10L, 10L, "reject");
  }

  @Test(expected = IllegalOperationException.class)
  public void testApproveReviewInWrongExamstate() throws ExamiburException {
    fakeLogin(USER_JUERGEN_KOENIG);
    exerciseGradingService.approveReview(21L, 56L, "accept");
  }

  private void fakeLogin(String username) throws ExamiburException {
    AuthenticationInformation info = authenticationService.login(username, "***");
    AuthenticationUtil.setCurrentUser(info.getUser());
  }

  @After
  public void fakeLogout() {
    AuthenticationUtil.setCurrentUser(null);
  }
}
