package ch.examibur.ui.app.controller;

import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import spark.Request;
import spark.Response;

public class ExerciseGradingController {

  /**
   * Adds a new grading or review to a specific solution and redirects back to that solution.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return nothing to return
   * @throws InvalidParameterException
   *           if an id parameter is negative or < 0.
   */
  public String addExerciseGrading(Request request, Response response)
      throws InvalidParameterException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request,
        UrlParameter.PARTICIPANT_ID);
    long solutionId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    response.redirect(RouteBuilder.toExerciseSolution(examId, participantId, solutionId));
    return null;
  }

}
