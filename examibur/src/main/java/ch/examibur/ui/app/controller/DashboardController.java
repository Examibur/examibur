package ch.examibur.ui.app.controller;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.User;
import ch.examibur.service.ExamService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.url.Link;
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
   * @throws ExamiburException
   *           throws {@link CommunicationException} if an exception during the communication
   *           occurs. throws {@link AuthorizationException} if the user is not authorized.
   */
  public String displayDashboard(Request request, Response response) throws ExamiburException {
    User user = request.attribute(RequestAttributes.USER);
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exams", examService.getExamsForAuthor(user.getId()));
    model.put("reviews", examService.getExamsForAuthor(user.getId(), ExamState.REVIEW));
    model.put("corrections", examService.getExamsForAuthor(user.getId(), ExamState.CORRECTION));
    return engine.render(model, "views/dashboardView.ftlh");
  }

  /**
   * Add / in the breadcrumbs.
   */
  public void addBreadCrumb(Request request, Response response) {
    RequestHelper.pushBreadCrumb(request, "Dashboard", Link.toDashboard());
  }
}
