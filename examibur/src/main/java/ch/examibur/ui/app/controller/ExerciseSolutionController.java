package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.ui.app.util.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseSolutionController extends Controller {

  public ExerciseSolutionController() {
    controllerName = "ExerciseSolutionController";
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
    model.put("title", controllerName);
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
    model.put("title", controllerName);
    return new TemplateRenderer().render(model, "404.ftl");
  }

  @Override
  public void route() {
    ExerciseGradingController exerciseGradingController = new ExerciseGradingController();

    get("/", this::listAll);

    path("/:exerciseId", () -> {
      get("/", this::show);

      path("/gradings", () -> {
        exerciseGradingController.route();
      });
    });
  }

}
