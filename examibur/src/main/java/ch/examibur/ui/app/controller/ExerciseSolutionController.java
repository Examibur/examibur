package ch.examibur.ui.app.controller;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.exercisegrading.ExerciseGradingService;
import ch.examibur.business.exercisesolution.ExerciseSolutionService;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class ExerciseSolutionController implements Controller {

  private final Renderer engine;
  private final ExerciseSolutionService exerciseSolutionService;
  private final ExerciseGradingService exerciseGradingService;

  public static final String QUERY_PARAM_QUERY_NEXT = "querynext";
  public static final String QUERY_PARAM_BROWSE = "browse";
  public static final String BROWSE_PARTICIPATIONS = "participations";

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
   * @throws NotFoundException
   *           if the exerciseSolution is not found
   * @throws AuthorizationException
   *           if the user is not authorized to display this {@link ExerciseSolution}
   * @throws IOException
   *           if an exception during the communication occurs
   * 
   */
  public String displayExerciseSolution(Request request, Response response)
      throws NotFoundException, AuthorizationException, IOException {

    long exerciseSolutionId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.SOLUTION_ID);
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);

    if (request.queryParams(QUERY_PARAM_BROWSE) != null) {
      model.put("browse", request.queryParams(QUERY_PARAM_BROWSE));
    }

    if (request.queryParams(QUERY_PARAM_QUERY_NEXT) != null) {
      long currentExerciseSolutionId = RoutingHelpers.getLongUrlParameter(request,
          UrlParameter.SOLUTION_ID);
      ExerciseSolution nextExerciseSolution = exerciseSolutionService
          .getExerciseSolutionFromNextParticipation(currentExerciseSolutionId);

      String target;
      long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
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

    model.put("exerciseSolution", exerciseSolutionService.getExerciseSolution(exerciseSolutionId));
    model.put("grading", exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId));
    model.put("review", exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId));
    return engine.render(model, "exerciseSolutionView.ftl");
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
   * Adds breadcurmb for `solutions/`.
   */
  public void addBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabenlösungen",
        RouteBuilder.toExerciseSolutions(examId, participantId));
  }

  /**
   * Adds breadcurmb for `solutions/:solutionsId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long solutionId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    RequestHelper.pushBreadCrumb(request, "Aufgabenlösung #" + solutionId,
        RouteBuilder.toExerciseSolution(examId, participantId, solutionId));
  }

}
