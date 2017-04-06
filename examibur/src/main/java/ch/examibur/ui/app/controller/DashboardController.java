package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.business.exam.ExamService;
import ch.examibur.ui.app.factory.ExamControllerFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;

public class DashboardController extends Controller {

  @Inject
  ExamControllerFactory examControllerFactory;
  
  private final ExamService examService;

  /**
   * Constructor.
   * 
   * @param preController
   *          the pre controller
   * @param examService
   *          the exam service implementation
   */
  @Inject
  public DashboardController(@Assisted Controller preController, ExamService examService) {
    super(preController, "");
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
    Map<String, Object> model = new HashMap<>();
    model.put("exams", examService.getExamsForAuthor(userId));
    return render(model, "dashboard.ftl");
  }

  @Override
  public void route() {
    ExamController examController = examControllerFactory.create(this);

    get("/", this::displayDashboard);

    path(examController.relativePath, examController::route);
  }

}
