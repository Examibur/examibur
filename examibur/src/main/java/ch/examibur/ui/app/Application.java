package ch.examibur.ui.app;

import static spark.Spark.staticFiles;

import ch.examibur.ui.app.controller.RootController;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  @Override
  public void init() {

    staticFiles.location("/public");

    RootController rootController = new RootController();
    rootController.route();

  }
}
