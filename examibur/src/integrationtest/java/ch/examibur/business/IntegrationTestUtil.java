package ch.examibur.business;

import ch.examibur.integration.IntegrationEntrypoint;
import ch.examibur.ui.app.module.ServiceModule;

import com.google.inject.Injector;

public class IntegrationTestUtil {
  public static final Injector INJECTOR = initInjector();

  private IntegrationTestUtil() {
  }

  private static Injector initInjector() {
    Injector baseInjector = new IntegrationEntrypoint().init();
    return baseInjector.createChildInjector(new ServiceModule());

  }
}
