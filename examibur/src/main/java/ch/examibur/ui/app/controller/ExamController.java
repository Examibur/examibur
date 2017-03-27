package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import ch.examibur.ui.app.util.TemplateRenderer;

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
  public String show(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "404.ftl");
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
  public String listAll(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "404.ftl");
  }

  /**
   * Updates a specific exam.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String update(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "404.ftl");
  }

  @Override
  public void route() {
    ExerciseController exerciseController = new ExerciseController(this);
    ExamParticipationController examParticipationController = new ExamParticipationController(this);

    get("/", this::listAll);

    path("/" + PARAM_EXAM_ID, () -> {
      get("/", this::show);
      post("/", this::update);

      path(exerciseController.relativePath, () -> {
        exerciseController.route();
      });

      path(examParticipationController.relativePath, () -> {
        examParticipationController.route();
      });
    });
  }

}
