package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.get;
import static spark.Spark.path;

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
  public String displayExerciseSolution(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return render(model, "404.ftl");
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
  public String listExerciseSolutions(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return render(model, "404.ftl");
  }

  @Override
  public void route() {
    ExerciseGradingController exerciseGradingController = new ExerciseGradingController(this);

    get("/", this::listExerciseSolutions);

    path("/" + PARAM_SOLUTION_ID, () -> {
      get("/", this::displayExerciseSolution);

      path(exerciseGradingController.relativePath, () -> {
        exerciseGradingController.route();
      });
    });
  }

}
