package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exercise.ExerciseService;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;

public class DashboardController extends Controller {

  private final ExamService examService;
  private final ExerciseService exerciseService;

  /**
   * Constructor.
   * 
   * @param preController
   *          the pre controller
   * @param examService
   *          the exam service implementation
   * @param exerciseService
   *          the exercise service implementation
   */
  public DashboardController(Controller preController, ExamService examService,
      ExerciseService exerciseService) {
    super(preController, "");
    this.examService = examService;
    this.exerciseService = exerciseService;
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
    ExamController examController = new ExamController(this, examService, exerciseService);

    get("/", this::displayDashboard);

    path(examController.relativePath, examController::route);
  }

}
