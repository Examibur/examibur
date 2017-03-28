package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.get;
import static spark.Spark.path;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class DashboardController extends Controller {

  public DashboardController(Controller preController) {
    super(preController, "");
  }

  /**
   * Returns the dashboard page.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String displayDashboard(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return render(model, "dashboard.ftl");
  }

  @Override
  public void route() {
    ExamController examController = new ExamController(this);

    get("/", this::displayDashboard);

    path(examController.relativePath, () -> {
      examController.route();
    });
  }

}
