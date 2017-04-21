package ch.examibur.ui.app.controller;

import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
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
    long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Aufgaben", RouteBuilder.toExercises(examId));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long exerciseId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXERCISE_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabe #" + exerciseId,
        RouteBuilder.toExercise(examId, exerciseId));

  }
}
