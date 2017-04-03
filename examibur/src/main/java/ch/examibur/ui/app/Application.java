package ch.examibur.ui.app;

import static spark.Spark.staticFiles;

import ch.examibur.integration.utils.Entrypoint;
import ch.examibur.integration.utils.IntegrationEntrypoint;
import ch.examibur.ui.app.controller.RootController;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  @Override
  public void init() {

    // TODO: Properly initialize other layers
    Entrypoint integrationEntry = new IntegrationEntrypoint();
    integrationEntry.init();
    
    staticFiles.location("/public");

    RootController rootController = new RootController();
    rootController.route();

  }
}
