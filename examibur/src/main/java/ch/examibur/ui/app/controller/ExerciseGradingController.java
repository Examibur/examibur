package ch.examibur.ui.app.controller;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import com.google.inject.Inject;
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
   * @return nothing to return --> redirect to ExerciseSolution
   * @throws InvalidParameterException
   *           if an id parameter is negative or < 0.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if the id is < 0. throws
   *           {@link NotFoundException} if the {@link ExerciseSolution} with the given id is not
   *           found. throws {@link AuthorizationException} if the user is not authorized. throws
   *           {@link CommunicationException} if an exception during the communication occurs.
   */
  public String addExerciseGrading(Request request, Response response) throws ExamiburException {
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
