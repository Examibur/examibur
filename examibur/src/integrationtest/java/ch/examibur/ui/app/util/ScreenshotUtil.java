package ch.examibur.ui.app.util;

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
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
      File[] listFiles = destination.listFiles();
      if (destination.exists() && listFiles != null) {
        for (File screenshot : listFiles) {
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
    assertScreenshots(new AShot(), 2);
  }

  /**
   * Advanced way to verify screenshots with configurable {@link AShot} object parameter. Will use
   * the calling methods Name as sceneName.
   *
   * @param ashot
   *          A predefined {@link AShot} instance to configure the screenshot.
   */
  public static void assertScreenshots(AShot ashot) {
    assertScreenshots(ashot, 2);
  }

  private static void assertScreenshots(AShot ashot, int stackTraceId) {
    String callingMethod = new Exception().getStackTrace()[stackTraceId].getMethodName();
    String referenceClassName = new Exception().getStackTrace()[stackTraceId].getClassName();
    Class<?> referenceClass;
    try {
      referenceClass = Thread.currentThread().getContextClassLoader().loadClass(referenceClassName);
    } catch (ClassNotFoundException e) {
      throw new AssertionError(e);
    }

    assertScreenshots(callingMethod, referenceClass, ashot);
  }

  /**
   * Takes multiple screenshot of the given View shown in the driver (using `getDriver()`) and
   * compares it with the reference screenshots.
   * 
   * @param sceneName
   *          The name of the "scene" (current screen) which is captured.
   * @param referenceScreenshotClass
   *          The class used to load the reference screenshots using `getResourceAsStream`.
   * @param ashot
   *          A predefined {@link AShot} instance to configure the screenshot.
   */
  public static void assertScreenshots(String sceneName, Class<?> referenceScreenshotClass,
      AShot ashot) {
    setUp();
    try {

      WebDriver driver = getDriver();
      Set<Coords> ignoredCoords = getCoords(ashot.getIgnoredLocators());
      Screenshot actualScreenshot = ashot.shootingStrategy(ShootingStrategies.viewportPasting(100))
          .takeScreenshot(driver);

      String screenshotName = format("{0}_{1, number,#}w.png", sceneName, 800);

      BufferedImage actual = actualScreenshot.getImage();
      ImageIO.write(actual, SCREENSHOT_FILE_FORMAT,
          new File(SCREENSHOT_DESTINATION + screenshotName));
      InputStream referenceStream = referenceScreenshotClass.getResourceAsStream(screenshotName);
      if (referenceStream == null) {
        throw new AssertionError("No Reference image found: " + screenshotName);
      }
      Screenshot referenceScreenshot = new Screenshot(ImageIO.read(referenceStream));
      referenceScreenshot.setIgnoredAreas(ignoredCoords);

      ImageDiff diff = new ImageDiffer().makeDiff(referenceScreenshot, actualScreenshot);
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

  private static Set<Coords> getCoords(Set<By> ignoredLocators) {
    WebDriver driver = getDriver();
    Set<Coords> ignoredCoords = new HashSet<>();
    for (By ignoredLocator : ignoredLocators) {
      List<WebElement> ignoredElements = driver.findElements(ignoredLocator);
      for (WebElement ignoredElement : ignoredElements) {
        Point point = ignoredElement.getLocation();
        Dimension dimension = ignoredElement.getSize();
        ignoredCoords.add(
            new Coords(point.getX(), point.getY(), dimension.getWidth(), dimension.getHeight()));
      }
    }
    return ignoredCoords;
  }

  /**
   * Returns a singleton Webdriver instance. Don't make any assumptions about the Returned driver!
   * The WebDriver might be a Proxy for multiple Browsers (in the future).
   * 
   * @return The (singleton) instance of a WebDriver.
   */
  public static synchronized WebDriver getDriver() {
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
