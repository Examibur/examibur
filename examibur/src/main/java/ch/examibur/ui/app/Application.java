package ch.examibur.ui.app;

import static spark.Spark.staticFiles;

import ch.examibur.integration.Entrypoint;
import ch.examibur.integration.IntegrationEntrypoint;
import ch.examibur.ui.app.controller.RootController;
import ch.examibur.ui.app.module.FactoryModule;
import ch.examibur.ui.app.module.ServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  @Override
  public void init() {

    // TODO: Properly initialize other layers
    Entrypoint integrationEntry = new IntegrationEntrypoint();
    integrationEntry.init();

    staticFiles.location("/public");

    Injector injector = Guice.createInjector(new ServiceModule(), new FactoryModule());
    final RootController rootController = injector.getInstance(RootController.class);

    rootController.route();
  }
}
