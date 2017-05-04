package ch.examibur.business;

import ch.examibur.business.service.ServiceModule;
import ch.examibur.integration.IntegrationEntrypoint;
import com.google.inject.Injector;

public class IntegrationTestUtil {
  private static Injector injector;
  private static RuntimeException exception;

  private IntegrationTestUtil() {
  }

  private static Injector initInjector() {
    if (exception != null) {
      throw exception;
    }
    try {
      Injector baseInjector = new IntegrationEntrypoint().init();
      return baseInjector.createChildInjector(new ServiceModule());
    } catch (RuntimeException e) {
      exception = e;
      throw e;
    }
  }

  /**
   * Get the singleton testing injector.
   */
  public static Injector getInjector() {
    if (injector == null) {
      injector = initInjector();
    }
    return injector;

  }
}
