package ch.examibur.ui.app;

import static ch.examibur.ui.app.util.ScreenshotUtil.assertScreenshots;
import static ch.examibur.ui.app.util.ScreenshotUtil.getDriver;

import ch.examibur.business.DatabaseResource;
import ch.examibur.domain.aggregation.BrowseSolutionsMode;
import ch.examibur.ui.app.url.Link;
import ch.examibur.ui.app.util.RandomBlockJUnit4ClassRunner;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;

@RunWith(RandomBlockJUnit4ClassRunner.class)
public class UiTest {

  private static final String USER_JUERGEN_KOENIG = "juergen.koenig";
  private static final String USER_STEFAN_BOEHM = "stefan.boehm";
  private static final String USER_CHRISTINA_THEISS = "christina.theiss";

  @Rule
  public final DatabaseResource res = new DatabaseResource();

  private static final String TEST_URL = System.getenv("UI_TEST_URL");

  /**
   * Do logout if not yet done.
   */
  @After
  public void ensureLogout() {
    getDriver().manage().deleteAllCookies();
  }

  @Test
  public void testDashboardUi() throws IOException {
    login(USER_JUERGEN_KOENIG);
    getDriver().get(TEST_URL);
    assertScreenshots();
  }

  @Test
  public void testDashboardUiWithoutExams() throws IOException {
    login(USER_CHRISTINA_THEISS);
    getDriver().get(TEST_URL);
    assertScreenshots();
  }

  @Test
  public void testLogin() throws IOException {
    getDriver().get(TEST_URL);
    assertScreenshots();
  }

  @Test
  public void testLogout() throws IOException {
    login(USER_JUERGEN_KOENIG);
    getDriver().get(TEST_URL);
    getDriver().findElement(By.id("user-details")).click();
    assertScreenshots();
    getDriver().findElement(By.id("logout")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().equals(TEST_URL + Link.toLogin());
    });
  }

  @Test
  public void testLoginRef() throws IOException {
    getDriver().get(TEST_URL + Link.toExam(2));
    Assert.assertEquals(TEST_URL + "/login/?ref=/exams/2/", getDriver().getCurrentUrl());
    login(USER_JUERGEN_KOENIG, false);
    Assert.assertEquals(TEST_URL + Link.toExam(2), getDriver().getCurrentUrl());
  }

  @Test
  public void testExamInformationTabUi() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExam(1);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamReportTabUi() throws IOException, InterruptedException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toReports(7);
    getDriver().get(testUrl);

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().findElement(By.className("highcharts-container")).isDisplayed();
    });
    Thread.sleep(1000);
    assertScreenshots();
  }

  @Test
  public void testExamReportTabUiReportRetrievalNotPossible() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toReports(2);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamReportTabUiMissingGradings() throws IOException, InterruptedException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toReports(8);
    getDriver().get(testUrl);

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().findElement(By.className("highcharts-container")).isDisplayed();
    });
    Thread.sleep(1000);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationsTab() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExamParticipations(7);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationsTabMissingGradings() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExamParticipations(8);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationsInfoTab() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExamParticipation(7, 14);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationsInfoTabMissingGrading() {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + Link.toExamParticipation(8, 18);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInApprovalWithApprovalPending() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(4, 4, 10, null);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithoutReview() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(5, 7, 19, null);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithReview() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(5, 7, 21, null);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithGrading() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(7, 13, 37, null);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithoutGrading() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(8, 17, 49, null);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamsList() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExams();
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testAddGradingToExerciseSolution() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(8, 17, 50, null);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("points-addgrading")).sendKeys("1");
    getDriver().findElement(By.id("comment-addgrading"))
        .sendKeys("Diese Lösung ist nicht korrekt.");
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys("1 Punkt für Argumentation");
    getDriver().findElement(By.id("submit-addgrading")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grading-panel")));
    assertScreenshots(
        new AShot().addIgnoredElement(By.cssSelector("#grading-panel > .panel-heading")));
  }

  @Test
  public void testAddGradingToExerciseSolutionAndContinue() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 17, 50, BrowseSolutionsMode.BY_PARTICIPATION);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("points-addgrading")).sendKeys("1");
    getDriver().findElement(By.id("comment-addgrading"))
        .sendKeys("Diese Lösung ist nicht korrekt.");
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys("1 Punkt für Argumentation");
    getDriver().findElement(By.id("submit-addgrading-continue")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/51");
    });
    assertScreenshots();
  }

  @Test
  public void testAddReviewToExerciseSolution() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(6, 12, 35, null);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("points-addgrading")).sendKeys("0");
    getDriver().findElement(By.id("comment-addgrading")).sendKeys("");
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys("Die Antwort fehlt komplett");
    getDriver().findElement(By.id("submit-addgrading")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-panel")));
    assertScreenshots(
        new AShot().addIgnoredElement(By.cssSelector("#review-panel  > .panel-heading")));
  }

  @Test
  public void testAcceptReview() throws IOException {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + Link.toExerciseSolution(4, 4, 10, null);
    getDriver().get(testUrl);
    WebElement acceptButton = getDriver().findElement(By.id("accept-review"));
    acceptButton.click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until(ExpectedConditions.invisibilityOf(acceptButton));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-panel")));

    assertScreenshots();
  }

  @Test
  public void testRejectReview() throws IOException {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + Link.toExerciseSolution(4, 4, 10, null);
    getDriver().get(testUrl);
    WebElement rejectButton = getDriver().findElement(By.id("reject-review"));
    rejectButton.click();
    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until(ExpectedConditions.invisibilityOf(rejectButton));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-panel")));
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByExercise() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolution(8, 17, 51, null);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-by-exercise")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/?browse=exercise");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByExerciseQueryNext() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 17, 51, BrowseSolutionsMode.BY_EXERCISE);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-next")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/54");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByExerciseQueryLast() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 18, 54, BrowseSolutionsMode.BY_EXERCISE);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-next")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return !getDriver().getCurrentUrl().contains("/solutions");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByParticipationQueryNext() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 18, 53, BrowseSolutionsMode.BY_PARTICIPATION);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-next")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/54");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByParticipationQueryLast() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 17, 54, BrowseSolutionsMode.BY_PARTICIPATION);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-next")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return !getDriver().getCurrentUrl().contains("/solutions");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionWithWrongParameter() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl =
        TEST_URL + Link.toExerciseSolution(8, 17, 51, null) + "query-next/?browse=wrong";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testQueryFirstExerciseSolutionByParticipation() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExamParticipation(8, 17);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-by-participation")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/49");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryFirstExerciseSolutionByParticipations() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toQueryFirstSolutionByParticipations(8);
    getDriver().get(testUrl);

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/49");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryFirstExerciseSolutionByExercise() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExercise(8, 16);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("query-by-exercise")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/49");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryFirstExerciseSolutionByExercises() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toQueryFirstSolutionByExercises(8);
    getDriver().get(testUrl);

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/49");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryFirstExerciseSolutionWithWrongParameter() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExamParticipation(8, 17) + "query-by-wrong/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationExercisesUi() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExerciseSolutions(5, 7);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationExercisesUiInCorrection() {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + Link.toExerciseSolutions(8, 17);
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseOverviewUi() {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + "/exams/8/exercises/16/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseParticipantsUi() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/5/exercises/8/participants/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseParticipantsMissingGradingsUi() {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + "/exams/8/exercises/16/participants/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testXssVulnerability() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/50";
    getDriver().get(testUrl);

    String xssDivId = "xss-test";
    String xssTest = "<script>var div = document.createElement('div'); div.id = '" + xssDivId
        + "'; document.body.appendChild(div);</script>";

    getDriver().findElement(By.id("points-addgrading")).sendKeys("1");
    getDriver().findElement(By.id("comment-addgrading")).sendKeys(xssTest);
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys(xssTest);
    getDriver().findElement(By.id("submit-addgrading")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grading-panel")));

    Assert.assertTrue("XSS possible!", getDriver().findElements(By.id(xssDivId)).isEmpty());
  }

  @Test
  public void testTransitionToReview() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExam(7L);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("start-transition")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().findElement(By.id("notification-message")).isDisplayed();
    });

    Assert.assertTrue("Transition not successful!", getDriver()
        .findElement(By.id("notification-message")).getAttribute("class").contains("bg-success"));
    assertScreenshots();
  }

  @Test
  public void testTransitionWithUnfinishedExerciseSolutions() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + Link.toExam(6L);
    getDriver().get(testUrl);
    getDriver().findElement(By.id("start-transition")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      return getDriver().findElement(By.id("notification-message")).isDisplayed();
    });

    Assert.assertTrue("No error message displayed after impossible transition!", getDriver()
        .findElement(By.id("notification-message")).getAttribute("class").contains("bg-danger"));
    assertScreenshots();
  }

  private void login(String username) {
    login(username, true);
  }

  private void login(String username, boolean goExplicitToLoginPage) {
    if (goExplicitToLoginPage) {
      getDriver().get(TEST_URL + Link.toLogin());
    }
    getDriver().findElement(By.id("username-login")).sendKeys(username);
    getDriver().findElement(By.id("password-login")).sendKeys("***");
    getDriver().findElement(By.id("submit-login")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 25);
    wait.until((x) -> {
      Cookie cookie = x.manage().getCookieNamed("authentication-token");
      return cookie != null && !getDriver().getCurrentUrl().contains(Link.toLogin());
    });

  }

}
