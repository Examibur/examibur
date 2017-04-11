package ch.examibur.ui.app;

import static spark.Spark.staticFiles;

import ch.examibur.business.BusinessEntrypoint;
import ch.examibur.business.Entrypoint;
import ch.examibur.ui.app.controller.RootController;
import ch.examibur.ui.app.module.FactoryModule;
import ch.examibur.ui.app.module.ServiceModule;

import com.google.inject.CreationException;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.servlet.SparkApplication;

public class Application implements SparkApplication {
  private final Logger logger = LoggerFactory.getLogger(Application.class);

  @Override
  public void init() {
    staticFiles.location("/public");
    try {
      Entrypoint businessEntry = new BusinessEntrypoint();
      Injector parentInjector = businessEntry.init();
      Injector injector = parentInjector.createChildInjector(new ServiceModule(),
          new FactoryModule());

      final RootController rootController = injector.getInstance(RootController.class);
      rootController.route();
    } catch (CreationException | ProvisionException e) {
      logger.error("Failed to intialize Guice", e);
      throw e;
    }
  }
}
