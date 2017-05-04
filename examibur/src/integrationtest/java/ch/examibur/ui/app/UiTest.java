package ch.examibur.ui.app;

import static ch.examibur.ui.app.util.ScreenshotUtil.assertScreenshots;
import static ch.examibur.ui.app.util.ScreenshotUtil.getDriver;

import ch.examibur.business.DatabaseResource;
import java.io.IOException;
import org.junit.ClassRule;
import org.junit.Test;

public class UiTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private static final String TEST_URL = System.getenv("UI_TEST_URL");

  @Test
  public void testDashboardUi() throws IOException {
    getDriver().get(TEST_URL);
    assertScreenshots();
  }

  @Test
  public void testExamInformationTabUi() throws IOException {
    final String testUrl = TEST_URL + "/exams/1/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInApprovalWithApprovalPending() throws IOException {
    final String testUrl = TEST_URL + "/exams/4/participants/4/solutions/10";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithoutReview() throws IOException {
    final String testUrl = TEST_URL + "/exams/5/participants/7/solutions/19";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithReview() throws IOException {
    final String testUrl = TEST_URL + "/exams/5/participants/7/solutions/21";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithGrading() throws IOException {
    final String testUrl = TEST_URL + "/exams/7/participants/13/solutions/37";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithoutGrading() throws IOException {
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/49";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamsList() throws IOException {
    final String testUrl = TEST_URL + "/exams";
    getDriver().get(testUrl);
    assertScreenshots();
  }
}
