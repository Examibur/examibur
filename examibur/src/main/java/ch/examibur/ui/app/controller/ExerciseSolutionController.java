package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.ui.app.util.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseSolutionController extends Controller {
  
  public static final String PARAM_SOLUTION_ID = ":solutionId";
  public static final String PATH = "/solutions";

  public ExerciseSolutionController(Controller preController) {
    super(preController, "/solutions");
  }

  /**
   * Returns a specific exercise solution.
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
   * Returns a list of exercise solutions.
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

  @Override
  public void route() {
    ExerciseGradingController exerciseGradingController = new ExerciseGradingController(this);

    get("/", this::listAll);

    path("/" + PARAM_SOLUTION_ID, () -> {
      get("/", this::show);

      path(exerciseGradingController.relativePath, () -> {
        exerciseGradingController.route();
      });
    });
  }

}
