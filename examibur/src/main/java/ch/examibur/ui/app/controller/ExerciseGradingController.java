package ch.examibur.ui.app.controller;

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
   */
  public String addExerciseGrading(Request request, Response response) {
    long examId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long solutionId = RoutingHelpers.getLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    response.redirect(RouteBuilder.toExerciseSolution(examId, participantId, solutionId));
    return null;
  }

}
