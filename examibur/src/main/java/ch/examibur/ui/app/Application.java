package ch.examibur.ui.app;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.staticFiles;

import ch.examibur.ui.app.controller.DashboardController;
import ch.examibur.ui.app.controller.ExceptionController;
import ch.examibur.ui.app.filter.Filters;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  @Override
  public void init() {

    staticFiles.location("/public");

    DashboardController dashboardController = new DashboardController();
    ExceptionController exceptionController = new ExceptionController();

    before("*", Filters.addTrailingSlashes);

    dashboardController.route();
    exceptionController.route();

    after("*", Filters.addGzipHeader);

  }
}
