package ch.examibur.ui.app;

import static ch.examibur.ui.app.util.ScreenshotUtil.assertScreenshots;
import static ch.examibur.ui.app.util.ScreenshotUtil.getDriver;

import ch.examibur.business.DatabaseResource;

import java.io.IOException;

import org.junit.ClassRule;
import org.junit.Test;

public class UITest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private final String URL = System.getenv("UI_TEST_URL");

  @Test
  public void testDashboardUI() throws IOException {
    getDriver().get(URL);
    assertScreenshots();
  }

}
