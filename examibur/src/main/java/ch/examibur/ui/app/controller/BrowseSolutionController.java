package ch.examibur.ui.app.controller;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.Exercise;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.ui.app.routing.QueryParameter;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import com.google.inject.Inject;
import spark.Redirect;
import spark.Request;
import spark.Response;

public class BrowseSolutionController implements Controller {

  private final ExerciseSolutionService exerciseSolutionService;

  /**
   * Constructor.
   * 
   * @param exerciseSolutionService
   *          the service to access exerciseSolutions
   */
  @Inject
  public BrowseSolutionController(ExerciseSolutionService exerciseSolutionService) {
    this.exerciseSolutionService = exerciseSolutionService;
  }

  /**
   * redirects to the next unprocessed {@link ExerciseSolution} of the same
   * {@link ExamParticipation}. If the last ExerciseSolution is reached, it will redirect back to
   * the {@link Exam} it belongs to.
   */
  public String getFirstExerciseSolutionByParticipation(Request request, Response response)
      throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId =
        RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    return redirectToNextExerciseSolution(response, BrowseSolutionsMode.BY_PARTICIPATION, examId,
        participantId, 0);
  }

  /**
   * redirects to the next unprocessed {@link ExerciseSolution} of the same
   * {@link ExamParticipation}. If the last ExerciseSolution is reached, it will continue with the
   * next Participation. If the very last ExerciseSolution is reached, it will redirect back to the
   * {@link Exam} it belongs to.
   */
  public String getFirstExerciseSolutionByParticipations(Request request, Response response)
      throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    return redirectToNextExerciseSolution(response, BrowseSolutionsMode.BY_PARTICIPATIONS, examId,
        0, 0);
  }

  /**
   * redirects to the next unprocessed {@link ExerciseSolution} of the same {@link Exercise}. If the
   * last ExerciseSolution is reached, it will redirect back to the {@link Exam} it belongs to.
   */
  public String getFirstExerciseSolutionByExercise(Request request, Response response)
      throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long exerciseId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXERCISE_ID);
    return redirectToNextExerciseSolution(response, BrowseSolutionsMode.BY_EXERCISE, examId,
        exerciseId, 0);
  }

  /**
   * redirects to the next unprocessed {@link ExerciseSolution} of the same {@link Exercise}. If the
   * last ExerciseSolution is reached, it will continue with the next Exercise. If the very last
   * ExerciseSolution is reached, it will redirect back to the {@link Exam} it belongs to.
   */
  public String getFirstExerciseSolutionByExercises(Request request, Response response)
      throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    return redirectToNextExerciseSolution(response, BrowseSolutionsMode.BY_EXERCISES, examId, 0, 0);
  }

  /**
   * redirects to the next unprocessed {@link ExerciseSolution} according to the given
   * {@link BrowseSolutionsMode} as query parameter. If the last ExerciseSolution is reached, it
   * will redirect back to the {@link Exam} it belongs to.
   */
  public String getNextExerciseSolution(Request request, Response response)
      throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId =
        RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);
    long exerciseSolutionId =
        RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.SOLUTION_ID);
    String paramBrowse = request.queryParams(QueryParameter.BROWSE_SOLUTIONS.toString());
    BrowseSolutionsMode browseMode = BrowseSolutionsMode.forName(paramBrowse);

    return redirectToNextExerciseSolution(response, browseMode, examId, participantId,
        exerciseSolutionId);
  }

  private String redirectToNextExerciseSolution(Response response, BrowseSolutionsMode browseMode,
      long examId, long queryResourceId, long exerciseSolutionId) throws ExamiburException {
    ExerciseSolution nextExerciseSolution = exerciseSolutionService
        .getNextExerciseSolution(browseMode, examId, queryResourceId, exerciseSolutionId);
    String target;

    if (nextExerciseSolution != null) {
      long nextParticipantId = nextExerciseSolution.getParticipation().getId();
      long nextExerciseSolutionId = nextExerciseSolution.getId();
      target = RouteBuilder.toExerciseSolution(examId, nextParticipantId, nextExerciseSolutionId,
          browseMode);
    } else {
      target = RouteBuilder.toExam(examId);
    }
    response.redirect(target, Redirect.Status.FOUND.intValue());
    return null;
  }
}
