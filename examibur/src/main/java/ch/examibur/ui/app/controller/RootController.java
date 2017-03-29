package ch.examibur.ui.app.controller;

import static spark.Spark.after;
import static spark.Spark.before;

import ch.examibur.ui.app.filter.Filters;

public class RootController extends Controller {

  public RootController() {
    super(null, "");
  }

  @Override
  public void route() {
    DashboardController dashboardController = new DashboardController(this);
    ExceptionController exceptionController = new ExceptionController(this);

    before("*", Filters.addTrailingSlashes);

    dashboardController.route();
    exceptionController.route();

    after("*", Filters.addGzipHeader);
  }

}
