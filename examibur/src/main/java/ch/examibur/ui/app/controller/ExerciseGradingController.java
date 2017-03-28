package ch.examibur.ui.app.controller;

import static spark.Spark.post;

import ch.examibur.ui.app.util.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseGradingController extends Controller {

  public ExerciseGradingController(Controller preController) {
    super(preController, "/gradings");
  }
  
  /**
   * Adds a new grading or review to a specific exercise.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String addExerciseGrading(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "404.ftl");
  }

  @Override
  public void route() {
    post("/", this::addExerciseGrading);
  }

}
