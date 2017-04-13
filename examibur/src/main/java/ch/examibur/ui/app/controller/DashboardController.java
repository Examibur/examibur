package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exam.ExamService;
import ch.examibur.ui.app.util.Renderer;

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
   * @param preController
   *          the pre controller
   * @param examService
   *          the exam service implementation
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
    long userId = Long.valueOf(request.session().attribute("user"));
    Map<String, Object> model = request.attribute(MODEL);
    model.put("exams", examService.getExamsForAuthor(userId));
    return engine.render(model, "dashboard.ftl");
  }

}
