package ch.examibur.ui.app;

import static ch.examibur.ui.app.util.ScreenshotUtil.assertScreenshots;
import static ch.examibur.ui.app.util.ScreenshotUtil.getDriver;

import ch.examibur.business.DatabaseResource;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;

public class UiTest {

  private static final String USER_JUERGEN_KOENIG = "juergen.koenig";
  private static final String USER_STEFAN_BOEHM = "stefan.boehm";

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

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

    WebDriverWait wait = new WebDriverWait(getDriver(), 100);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().equals(TEST_URL + "login/");
    });
  }

  @Test
  public void testLoginRef() throws IOException {
    getDriver().get(TEST_URL + "/exams/2/");
    Assert.assertEquals(TEST_URL + "login/?ref=/exams/2/", getDriver().getCurrentUrl());
    login(USER_JUERGEN_KOENIG, false);
    Assert.assertEquals(TEST_URL + "exams/2/", getDriver().getCurrentUrl());
  }

  @Test
  public void testExamInformationTabUi() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/1/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamReportTabUi() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/7/reports";
    getDriver().get(testUrl);

    WebDriverWait wait = new WebDriverWait(getDriver(), 500);
    wait.until((x) -> {
      return getDriver().findElement(By.className("highcharts-container")).isDisplayed();
    });
    assertScreenshots();
  }

  @Test
  public void testExamReportTabUiReportRetrievalNotPossible() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/2/reports";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInApprovalWithApprovalPending() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/4/participants/4/solutions/10";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithoutReview() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/5/participants/7/solutions/19";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInReviewWithReview() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/5/participants/7/solutions/21";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithGrading() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/7/participants/13/solutions/37";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExerciseSolutionUiInCorrectionWithoutGrading() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/49";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamsList() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testAddGradingToExerciseSolution() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/50";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("points-addgrading")).sendKeys("1");
    getDriver().findElement(By.id("comment-addgrading"))
        .sendKeys("Diese Lösung ist nicht korrekt.");
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys("1 Punkt für Argumentation");
    getDriver().findElement(By.id("submit-addgrading")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grading-panel")));
    assertScreenshots(new AShot().addIgnoredElement(By.cssSelector("#grading-panel")));
  }

  @Test
  public void testAddReviewToExerciseSolution() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/6/participants/12/solutions/35";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("points-addgrading")).sendKeys("0");
    getDriver().findElement(By.id("comment-addgrading")).sendKeys("");
    getDriver().findElement(By.id("reasoning-addgrading")).sendKeys("Die Antwort fehlt komplett");
    getDriver().findElement(By.id("submit-addgrading")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-panel")));
    assertScreenshots(new AShot().addIgnoredElement(By.cssSelector("#review-panel")));
  }

  @Test
  public void testQueryExerciseSolutionByExercise() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/51";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("browse-solutions")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/?browse=exercise");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByExerciseQueryNext() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/51/?browse=exercise";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("querynext")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/54");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByExerciseQueryLast() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/18/solutions/54/?browse=exercise";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("querynext")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until((x) -> {
      return !getDriver().getCurrentUrl().contains("/solutions");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByParticipationQueryNext() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/18/solutions/53/?browse=participation";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("querynext")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until((x) -> {
      return getDriver().getCurrentUrl().contains("/solutions/54");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionByParticipationQueryLast() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/54/?browse=participation";
    getDriver().get(testUrl);
    getDriver().findElement(By.id("querynext")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    wait.until((x) -> {
      return !getDriver().getCurrentUrl().contains("/solutions");
    });
    assertScreenshots();
  }

  @Test
  public void testQueryExerciseSolutionWithWrongParameter() throws IOException {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL
        + "/exams/8/participants/17/solutions/51/?browse=wrong&querynext=";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationExercisesUi() {
    login(USER_JUERGEN_KOENIG);
    final String testUrl = TEST_URL + "/exams/5/participants/7/solutions/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  @Test
  public void testExamParticipationExercisesUiInCorrection() {
    login(USER_STEFAN_BOEHM);
    final String testUrl = TEST_URL + "/exams/8/participants/17/solutions/";
    getDriver().get(testUrl);
    assertScreenshots();
  }

  private void login(String username) {
    login(username, true);
  }

  private void login(String username, boolean goExplicitToLoginPage) {
    if (goExplicitToLoginPage) {
      getDriver().get(TEST_URL + "/login/");
    }
    getDriver().findElement(By.id("username-login")).sendKeys(username);
    getDriver().findElement(By.id("password-login")).sendKeys("***");
    getDriver().findElement(By.id("submit-login")).click();

    WebDriverWait wait = new WebDriverWait(getDriver(), 100);
    wait.until((x) -> {
      Cookie cookie = x.manage().getCookieNamed("authentication-token");
      return cookie != null && !getDriver().getCurrentUrl().contains("/login/");
    });

  }

}
