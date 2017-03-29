package ch.examibur.ui.app;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.staticFiles;

import ch.examibur.ui.app.controller.RootController;
import ch.examibur.ui.app.filter.Filters;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  @Override
  public void init() {

    staticFiles.location("/public");

    RootController rootController = new RootController();

    before("*", Filters.addTrailingSlashes);

    rootController.route();

    after("*", Filters.addGzipHeader);

  }
}
