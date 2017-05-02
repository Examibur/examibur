package ch.examibur.ui.app.controller;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.exercisegrading.ExerciseGradingService;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import com.google.inject.Inject;
import java.io.IOException;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

public class ExerciseGradingController {

  public static final String BODY_PARAM_POINTS = "points";
  public static final String BODY_PARAM_REASONING = "reasoning";
  public static final String BODY_PARAM_COMMENT = "comment";

  private final ExerciseGradingService exerciseGradingService;

  /**
   * Constructor.
   * 
   * @param exerciseGradingService
   *          the service to access exerciseGradings
   */
  @Inject
  public ExerciseGradingController(ExerciseGradingService exerciseGradingService) {
    this.exerciseGradingService = exerciseGradingService;
  }

  /**
   * Adds a new grading or review to a specific solution and redirects back to that solution.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return nothing to return
   */
  public String addExerciseGrading(Request request, Response response)
      throws NotFoundException, AuthorizationException, IOException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.PARTICIPANT_ID);
    long solutionId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    String comment = request.queryParams(BODY_PARAM_COMMENT);
    String reasoning = request.queryParams(BODY_PARAM_REASONING);
    Double points = RoutingHelpers.getUnsignedDoubleBodyParameter(request, BODY_PARAM_POINTS);

    exerciseGradingService.addGrading(solutionId, comment, reasoning, points);

    response.redirect(RouteBuilder.toExerciseSolution(examId, participantId, solutionId),
        HttpStatus.FOUND_302);
    return null;
  }

}
