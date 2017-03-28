package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExamController extends Controller {

  public static final String PARAM_EXAM_ID = ":examId";

  public ExamController(Controller preController) {
    super(preController, "/exams");
  }

  /**
   * Returns a specific exam.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String displayExam(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return render(model, "404.ftl");
  }

  /**
   * Returns a list of exams.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String listExams(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return render(model, "404.ftl");
  }

  /**
   * Updates a specific exam and redirects to the GET controller.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return nothing to return
   */
  public String updateExam(Request request, Response response) {
    response.redirect(request.pathInfo(), 302);
    return null;
  }

  @Override
  public void route() {
    ExerciseController exerciseController = new ExerciseController(this);
    ExamParticipationController examParticipationController = new ExamParticipationController(this);

    get("/", this::listExams);

    path("/" + PARAM_EXAM_ID, () -> {
      get("/", this::displayExam);
      post("/", this::updateExam);

      path(exerciseController.relativePath, () -> {
        exerciseController.route();
      });

      path(examParticipationController.relativePath, () -> {
        examParticipationController.route();
      });
    });
  }

}
