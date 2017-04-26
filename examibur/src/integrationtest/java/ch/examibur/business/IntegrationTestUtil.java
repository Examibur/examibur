package ch.examibur.business;

import com.google.inject.Injector;

public class IntegrationTestUtil {
  public static final Injector INJECTOR = initInjector();

  private IntegrationTestUtil() {
  }

  private static Injector initInjector() {
    return new BusinessEntrypoint().init();
  }
}
