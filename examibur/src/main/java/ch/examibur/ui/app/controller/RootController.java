package ch.examibur.ui.app.controller;

public class RootController extends Controller {

  public RootController() {
    super(null, "");
  }

  @Override
  public void route() {
    DashboardController dashboardController = new DashboardController(this);
    ExceptionController exceptionController = new ExceptionController(this);

    dashboardController.route();
    exceptionController.route();
  }

}
