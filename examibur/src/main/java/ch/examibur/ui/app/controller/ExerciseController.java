package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.ui.app.util.Renderer;

import com.google.inject.Inject;

import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseController implements Controller {

  public static final String PARAM_EXERCISE_ID = ":exerciseId";
  private final Renderer engine;

  @Inject
  public ExerciseController(Renderer engine) {
    this.engine = engine;
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
  public String displayExercise(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "404.ftl");
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
  public String listExercises(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "404.ftl");
  }

}
