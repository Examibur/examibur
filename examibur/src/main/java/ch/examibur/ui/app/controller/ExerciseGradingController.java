package ch.examibur.ui.app.controller;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.aggregation.BrowseSolutionsMode;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.exception.ValidationException;
import ch.examibur.ui.app.url.Link;
import ch.examibur.ui.app.url.QueryParameter;
import ch.examibur.ui.app.url.UrlHelpers;
import ch.examibur.ui.app.url.UrlParameter;
import com.google.inject.Inject;
import spark.Redirect;
import spark.Request;
import spark.Response;

public class ExerciseGradingController {

  public static final String BODY_PARAM_POINTS = "points";
  public static final String BODY_PARAM_REASONING = "reasoning";
  public static final String BODY_PARAM_COMMENT = "comment";
  public static final String BODY_PARAM_ACTION = "action";

  public static final String BODY_PARAM_APPROVAL = "approve-review";
  public static final String REVIEW_ACCEPT = "accept";
  public static final String REVIEW_REJECT = "reject";

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
    long examId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId =
        UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long solutionId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);

    String comment = request.queryParams(BODY_PARAM_COMMENT);
    String reasoning = request.queryParams(BODY_PARAM_REASONING);
    Double points = UrlHelpers.getUnsignedDoubleBodyParameter(request, BODY_PARAM_POINTS);
    String action = request.queryParams(BODY_PARAM_ACTION);

    exerciseGradingService.addGrading(solutionId, comment, reasoning, points);

    BrowseSolutionsMode browseMode = BrowseSolutionsMode
        .forName(request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString()));
    String target;
    if ("saveandcontinue".equals(action)) {
      target = Link.toQueryNextSolution(examId, participantId, solutionId, browseMode);
    } else {
      target = Link.toExerciseSolution(examId, participantId, solutionId, browseMode);
    }
    response.redirect(target, Redirect.Status.FOUND.intValue());
    return null;
  }

  /**
   * Either accept or reject a review during the approval phase.
   * 
   * @throws ExamiburException
   *           throws {@link NotFoundException} if the {@link ExerciseSolution} does not exist.
   *           Throws {@link IllegalOperationException} if the grading is not a review. Throws
   *           {@link InvalidParameterException} if an id is negative. Throws
   *           {@link ValidationException} if the approval result is invalid. Throws
   *           {@link AuthorizationException} if the user is not authorized to approve. Throws
   *           {@link CommunicationException} if an error during the communication occurs.
   */
  public String processApproval(Request request, Response response) throws ExamiburException {
    long examId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId =
        UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long solutionId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);
    long gradingId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.GRADING_ID);

    String acceptParam = request.queryParams(BODY_PARAM_APPROVAL);

    exerciseGradingService.approveReview(solutionId, gradingId, acceptParam);

    response.redirect(
        Link.toExerciseSolution(examId, participantId, solutionId,
            BrowseSolutionsMode
                .forName(request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString()))),
        Redirect.Status.FOUND.intValue());
    return null;
  }
}
