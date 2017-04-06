package ch.examibur.ui.app.controller;

import static spark.Spark.after;
import static spark.Spark.before;

import ch.examibur.ui.app.factory.DashboardControllerFactory;
import ch.examibur.ui.app.filter.Filters;
import com.google.inject.Inject;

public class RootController extends Controller {

  @Inject
  private DashboardControllerFactory dashboardControllerFactory;

  public RootController() {
    super(null, "");
  }

  @Override
  public void route() {
    final DashboardController dashboardController = dashboardControllerFactory.create(this);
    final ExceptionController exceptionController = new ExceptionController(this);

    before("*", Filters::addTrailingSlashes);
    before("*", Filters::handleAuthentication);

    dashboardController.route();
    exceptionController.route();

    after("*", Filters::addGzipHeader);
  }

}
