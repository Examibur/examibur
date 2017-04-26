package ch.examibur.ui.app.controller;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.exercise.ExerciseService;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExerciseController implements Controller {

  private final ExamService examService;
  private final ExerciseService exerciseService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   * @param exerciseService
   *          the service to access exercises
   */
  @Inject
  public ExerciseController(Renderer engine, ExamService examService,
      ExerciseService exerciseService) {
    this.engine = engine;
    this.examService = examService;
    this.exerciseService = exerciseService;
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
   * @throws AuthorizationException
   *           if the user is not authorized to display the exam or exercises.
   * @throws NotFoundException
   *           if the exam/exercises is/are not found.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  public String listExercises(Request request, Response response) throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("exercises", exerciseService.getExercises(examId));
    return engine.render(model, "examExercisesTab.ftl");
  }

  /**
   * Adds breadcurmb for `exercises/`.
   */
  public void addBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Aufgaben", RouteBuilder.toExercises(examId));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long exerciseId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXERCISE_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabe #" + exerciseId,
        RouteBuilder.toExercise(examId, exerciseId));

  }
}
