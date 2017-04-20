package ch.examibur.ui.app.controller;

import ch.examibur.ui.app.routing.Routes;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.RequestHelper;
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
    long examId = RequestHelper.getLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RequestHelper.getLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long solutionId = RequestHelper.getLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    response.redirect(Routes.SOLUTION.with(UrlParameter.EXAM_ID, examId)
        .with(UrlParameter.PARTICIPANT_ID, participantId).with(UrlParameter.SOLUTION_ID, solutionId)
        .url());
    return null;
  }

}
