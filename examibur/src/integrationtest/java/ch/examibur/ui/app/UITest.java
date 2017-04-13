package ch.examibur.ui.app;

import static ch.examibur.ui.app.util.ScreenshotUtil.assertScreenshots;
import static ch.examibur.ui.app.util.ScreenshotUtil.getDriver;

import java.io.IOException;

import org.junit.Test;

public class UITest {

  private final String URL = System.getenv("UI_TEST_URL");

  @Test
  public void testDashboardUI() throws IOException {
    getDriver().get(URL);
    assertScreenshots();
  }

}
