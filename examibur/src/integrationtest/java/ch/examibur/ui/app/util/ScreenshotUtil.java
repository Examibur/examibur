package ch.examibur.ui.app.util;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotUtil {

  private static final String SCREENSHOT_FILE_FORMAT = "png";
  private static final String SCREENSHOT_DESTINATION = "screenshots/";

  /**
   * Singleton instance of the current WebDriver.
   */
  private static WebDriver instance;

  /**
   * This flag ensures that the screenshots directory is cleaned when a test runs the first time.
   */
  private static boolean setUp;

  /**
   * Private constructor.
   */
  private ScreenshotUtil() {
    super();
  }

  /**
   * Prepares and cleans the destination directory. This method is only executed ONCE per run.
   */
  private static void setUp() {
    if (!setUp) {
      File destination = new File(SCREENSHOT_DESTINATION);
      if (destination.exists() && destination.isDirectory()) {
        for (File screenshot : destination.listFiles()) {
          assertTrue(screenshot.delete());
        }
      } else {
        destination.mkdirs();
      }
      setUp = true;
    }
  }

  /**
   * Simplest way to verify screenshots. Will use the calling methods Name as sceneName.
   */
  public static void assertScreenshots() {
    String callingMethod = new Exception().getStackTrace()[1].getMethodName();
    String referenceClassName = new Exception().getStackTrace()[1].getClassName();
    Class<?> referenceClass;
    try {
      referenceClass = Thread.currentThread().getContextClassLoader().loadClass(referenceClassName);
    } catch (ClassNotFoundException e) {
      throw new AssertionError(e);
    }

    assertScreenshots(callingMethod, referenceClass);
  }

  /**
   * Takes multiple screenshot of the given View shown in the driver (using `getDriver()`) and
   * compares it with the reference screenshots.
   * 
   * @param sceneName
   *          The name of the "scene" (current screen) which is captured.
   * @param referenceScreenshotClass
   *          The class used to load the reference screenshots using `getResourceAsStream`.
   */
  public static void assertScreenshots(String sceneName, Class<?> referenceScreenshotClass) {
    setUp();
    try {

      WebDriver driver = getDriver();

      Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
          .takeScreenshot(driver);

      String screenshotName = format("{0}_{1, number,#}w.png", sceneName, 800);

      BufferedImage actual = screenshot.getImage();
      ImageIO.write(actual, SCREENSHOT_FILE_FORMAT,
          new File(SCREENSHOT_DESTINATION + screenshotName));
      InputStream referenceStream = referenceScreenshotClass.getResourceAsStream(screenshotName);
      if (referenceStream == null) {
        throw new AssertionError("No Reference image found: " + screenshotName);
      }
      BufferedImage reference = ImageIO.read(referenceStream);

      ImageDiff diff = new ImageDiffer().makeDiff(reference, actual);
      if (diff.hasDiff()) {
        String diffDestination = SCREENSHOT_DESTINATION + "diff_" + screenshotName;
        ImageIO.write(diff.getMarkedImage(), SCREENSHOT_FILE_FORMAT, new File(diffDestination));

        throw new AssertionError(
            "Screenshots do not match! Checkout the diff for details at " + diffDestination);
      }

    } catch (IOException e) {
      throw new AssertionError(e);
    }

  }

  /**
   * Returns a singleton Webdriver instance. Don't make any assumptions about the Returned driver!
   * The WebDriver might be a Proxy for multiple Browsers (in the future).
   * 
   * @return The (singleton) instance of a WebDriver.
   */
  public static WebDriver getDriver() {
    if (instance == null) {
      DesiredCapabilities capability = DesiredCapabilities.firefox();
      try {
        instance = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), capability);
        if (instance.manage().window().getSize().width != 800) {
          instance.manage().window().setSize(new Dimension(800, 600));
        }
      } catch (MalformedURLException e) {
        // impossible...
      }
      Runtime.getRuntime().addShutdownHook(new Thread(instance::quit));
    }
    return instance;
  }

}
