package ch.examibur.ui.app.controller;

import ch.examibur.business.exam.ExamService;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public class DashboardController implements Controller {

  private final ExamService examService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   */
  @Inject
  public DashboardController(Renderer engine, ExamService examService) {
    this.engine = engine;
    this.examService = examService;
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
    long userId = request.attribute("user");
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exams", examService.getExamsForAuthor(userId));
    return engine.render(model, "dashboard.ftl");
  }

  /**
   * Add / in the breadcrumbs.
   */
  public void addBreadCrumb(Request request, Response response) {
    RequestHelper.pushBreadCrumb(request, "Dashboard", RouteBuilder.toDashboard());
  }
}
