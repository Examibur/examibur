package ch.examibur.ui.app.controller;

import ch.examibur.ui.app.routing.PartialUrl;
import ch.examibur.ui.app.routing.Routes;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExerciseController implements Controller {

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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "404.ftl");
  }

  /**
   * Adds breadcurmb for `exercises/`.
   */
  public void addBreadCrumb(Request request, Response response) {
    long examId = RequestHelper.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Aufgaben",
        Routes.EXERCISES.with(UrlParameter.EXAM_ID, examId));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RequestHelper.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long exerciseId = RequestHelper.getLongUrlParameter(request, UrlParameter.EXERCISE_ID);

    PartialUrl url = Routes.EXERCISE.with(UrlParameter.EXAM_ID, examId)
        .with(UrlParameter.EXERCISE_ID, exerciseId);
    RequestHelper.pushBreadCrumb(request, "Aufgabe #" + exerciseId, url);

  }
}
