package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.ui.app.util.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseController extends Controller {

  public static final String PARAM_EXERCISE_ID = ":exerciseId";

  public ExerciseController(Controller preController) {
    super(preController, "/exercises");
  }

  /**
   * Returns a specific exercise.
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
   * Returns a list of exercises.
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

    get("/", this::listAll);

    path("/" + PARAM_EXERCISE_ID, () -> {
      get("/", this::show);
    });
  }

}
