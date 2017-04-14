package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.ui.app.util.Renderer;

import com.google.inject.Inject;
import java.io.IOException;
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
   * @throws IOException
   *           if an exception during the communication occurs
   * @throws AuthorizationException
   *           if the user is not authorized
   */
  public String displayDashboard(Request request, Response response)
      throws AuthorizationException, IOException {
    long userId = request.attribute("user");
    Map<String, Object> model = request.attribute(MODEL);
    model.put("exams", examService.getExamsForAuthor(userId));
    return engine.render(model, "dashboard.ftl");
  }

}
