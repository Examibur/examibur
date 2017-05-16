package ch.examibur.ui.app.controller;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.BrowseSolutionsValue;
import ch.examibur.ui.app.routing.QueryParameter;
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

  private final Renderer engine;
  private final ExerciseSolutionService exerciseSolutionService;
  private final ExerciseGradingService exerciseGradingService;
  private final ExamParticipationService examParticipationService;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param exerciseSolutionService
   *          the service to access exerciseSolutions
   * @param exerciseGradingService
   *          the service to access exerciseGradings
   * @param examParticipationService
   *          the service to access examParticipations
   */
  @Inject
  public ExerciseSolutionController(Renderer engine,
      ExerciseSolutionService exerciseSolutionService,
      ExerciseGradingService exerciseGradingService,
      ExamParticipationService examParticipationService) {
    this.engine = engine;
    this.exerciseSolutionService = exerciseSolutionService;
    this.exerciseGradingService = exerciseGradingService;
    this.examParticipationService = examParticipationService;
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

    String paramBrowse = request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString());
    if (request.queryParams(QueryParameter.QUERY_NEXT_SOLUTION.toString()) != null) {
      ExerciseSolution nextExerciseSolution = getNextExerciseSolution(exerciseSolutionId,
          paramBrowse);
      redirectToNextExerciseSolution(request, response, nextExerciseSolution);
      return null;
    }

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);

    if (BrowseSolutionsValue.forName(paramBrowse) != null) {
      model.put(QueryParameter.BROWSE_SOLUTIONS.toString(), paramBrowse);
    }

    model.put("exerciseSolution", exerciseSolutionService.getExerciseSolution(exerciseSolutionId));
    model.put("grading", exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId));
    model.put("review", exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId));
    return engine.render(model, "views/exerciseSolutionView.ftlh");
  }

  private ExerciseSolution getNextExerciseSolution(long exerciseSolutionId, String paramBrowse)
      throws ExamiburException {
    BrowseSolutionsMode browseMode = BrowseSolutionsMode.forName(paramBrowse);
    if (browseMode != null) {
      return exerciseSolutionService.getNextExerciseSolution(browseMode, exerciseSolutionId);
    } else {
      InvalidParameterException invalidParameterException = new InvalidParameterException(
          "Query parameter '" + QueryParameter.BROWSE_SOLUTIONS.toString() + "' with value '"
              + paramBrowse + "' is not defined");
      LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }
  }

  private ExerciseSolution getFirstExerciseSolution(long resourceId, String paramBrowse)
      throws ExamiburException {
    BrowseSolutionsMode browseMode = BrowseSolutionsMode.forName(paramBrowse);
    if (browseMode != null) {
      return exerciseSolutionService.getFirstExerciseSolution(browseMode, resourceId);
    } else {
      InvalidParameterException invalidParameterException = new InvalidParameterException(
          "Query parameter '" + QueryParameter.BROWSE_SOLUTIONS.toString() + "' with value '"
              + paramBrowse + "' is not defined");
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
      target = RouteBuilder.toExerciseSolution(examId, participantId, nextExerciseSolutionId,
          BrowseSolutionsValue
              .forName(request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString())));
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
  public String listExerciseSolutions(Request request, Response response) throws ExamiburException {
    long examParticipationId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.PARTICIPANT_ID);

    String paramBrowse = request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString());
    if (request.queryParams(QueryParameter.QUERY_NEXT_SOLUTION.toString()) != null) {
      ExerciseSolution firstExerciseSolution = getFirstExerciseSolution(examParticipationId,
          paramBrowse);
      redirectToNextExerciseSolution(request, response, firstExerciseSolution);
      return null;
    }

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("participation", examParticipationService.getExamParticipation(examParticipationId));
    model.put("exerciseSolutionOverviews",
        exerciseSolutionService.getExerciseSolutionsForExamParticipation(examParticipationId));
    return engine.render(model, "views/examParticipationExerciseTab.ftlh");
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
        RouteBuilder.toExerciseSolution(examId, participantId, solutionId, BrowseSolutionsValue
            .forName(request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString()))));
  }

}
