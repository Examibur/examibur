package ch.examibur.ui.app.controller;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class ExerciseSolutionController implements Controller {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionController.class);

  public static final String QUERY_PARAM_QUERY_NEXT = "querynext";
  public static final String QUERY_PARAM_BROWSE = "browse";
  public static final String BROWSE_EXERCISE = "exercise";
  public static final String BROWSE_PARTICIPATION = "participation";
  public static final String BROWSE_EXERCISES = "exercises";
  public static final String BROWSE_PARTICIPATIONS = "participations";

  private final Renderer engine;
  private final ExerciseSolutionService exerciseSolutionService;
  private final ExerciseGradingService exerciseGradingService;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param exerciseSolutionService
   *          the service to access exerciseSolutions
   * @param exerciseGradingService
   *          the service to access exerciseGradings
   */
  @Inject
  public ExerciseSolutionController(Renderer engine,
      ExerciseSolutionService exerciseSolutionService,
      ExerciseGradingService exerciseGradingService) {
    this.engine = engine;
    this.exerciseSolutionService = exerciseSolutionService;
    this.exerciseGradingService = exerciseGradingService;
  }

  /**
   * Returns a specific exercise solution.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if a parameter is invalid. throws
   *           {@link NotFoundException} if the exerciseSolution is not found. throws
   *           {@link AuthorizationException} if the user is not authorized to display this
   *           {@link ExerciseSolution}. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   * 
   */
  public String displayExerciseSolution(Request request, Response response)
      throws ExamiburException {
    long exerciseSolutionId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.SOLUTION_ID);

    String paramBrowse = request.queryParams(QUERY_PARAM_BROWSE);
    if (request.queryParams(QUERY_PARAM_QUERY_NEXT) != null) {
      ExerciseSolution nextExerciseSolution = getNextExerciseSolution(exerciseSolutionId,
          paramBrowse);
      redirectToNextExerciseSolution(request, response, nextExerciseSolution);
      return null;
    }

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);

    if (paramBrowse != null
        && (paramBrowse.equals(BROWSE_EXERCISE) || paramBrowse.equals(BROWSE_PARTICIPATION))) {
      model.put(QUERY_PARAM_BROWSE, paramBrowse);
    }

    model.put("exerciseSolution", exerciseSolutionService.getExerciseSolution(exerciseSolutionId));
    model.put("grading", exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId));
    model.put("review", exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId));
    return engine.render(model, "exerciseSolutionView.ftl");
  }

  private ExerciseSolution getNextExerciseSolution(long exerciseSolutionId, String paramBrowse)
      throws ExamiburException {
    if (paramBrowse.equals(BROWSE_EXERCISE)) {
      return exerciseSolutionService.getExerciseSolutionFromNextParticipation(exerciseSolutionId);
    } else if (paramBrowse.equals(BROWSE_PARTICIPATION)) {
      return exerciseSolutionService.getNextExerciseSolutionFromParticipation(exerciseSolutionId);
    } else {
      InvalidParameterException invalidParameterException = new InvalidParameterException(
          "Query parameter '" + QUERY_PARAM_BROWSE + "' with value '" + paramBrowse
              + "' is not defined");
      LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }
  }

  private void redirectToNextExerciseSolution(Request request, Response response,
      ExerciseSolution nextExerciseSolution) throws ExamiburException {
    String target;
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    if (nextExerciseSolution != null) {
      long participantId = nextExerciseSolution.getParticipation().getId();
      long nextExerciseSolutionId = nextExerciseSolution.getId();
      target = RouteBuilder.addQueryParameter(
          RouteBuilder.toExerciseSolution(examId, participantId, nextExerciseSolutionId),
          QUERY_PARAM_BROWSE, request.queryParams(QUERY_PARAM_BROWSE));
    } else {
      target = RouteBuilder.toExam(examId);
    }
    response.redirect(target, HttpStatus.FOUND_302);
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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "404.ftl");
  }

  /**
   * Adds breadcrumb for `solutions/`.
   * 
   * @throws InvalidParameterException
   *           if an id parameter is not set or < 0.
   */
  public void addBreadCrumb(Request request, Response response) throws InvalidParameterException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.PARTICIPANT_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabenlösungen",
        RouteBuilder.toExerciseSolutions(examId, participantId));
  }

  /**
   * Adds breadcrumb for `solutions/:solutionsId`.
   * 
   * @throws InvalidParameterException
   *           if an id parameter is not set or < 0.
   */
  public void addSpecificBreadCrumb(Request request, Response response)
      throws InvalidParameterException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.PARTICIPANT_ID);
    long solutionId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabenlösung #" + solutionId,
        RouteBuilder.toExerciseSolution(examId, participantId, solutionId));
  }

}
