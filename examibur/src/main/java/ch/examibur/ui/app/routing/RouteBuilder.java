package ch.examibur.ui.app.routing;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RouteBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(RouteBuilder.class);

  private RouteBuilder() {
  }

  /**
   * Returns the absolute url to the dashboard.
   */
  public static String toDashboard() {
    return Route.ROOT.url();
  }

  /**
   * Returns the absolute url to the login page.
   */
  public static String toLogin() {
    return Route.LOGIN.url();
  }

  /**
   * Returns the absolute url to the logout page.
   */
  public static String toLogout() {
    return Route.LOGOUT.url();
  }

  /**
   * Returns the absolute url to the list of exams.
   */
  public static String toExams() {
    return Route.EXAMS.url();
  }

  /**
   * Returns the absolute url to the exam with the given id. It is the responsibility of the caller
   * to provide a valid id!
   */
  public static String toExam(long examId) {
    String url = Route.EXAM.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercises of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toExercises(long examId) {
    String url = Route.EXERCISES.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise with the given id of the given exam. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExercise(long examId, long exerciseId) {
    String url = Route.EXERCISE.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.EXERCISE_ID, exerciseId);
    return url;
  }

  /**
   * Returns the absolute url to all participants of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toExamParticipations(long examId) {
    String url = Route.PARTICIPANTS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the participants with the given id of the given exam. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExamParticipation(long examId, long participantId) {
    String url = Route.PARTICIPANT.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    return url;
  }

  /**
   * Returns the absolute url to all reports of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toReports(long examId) {
    String url = Route.REPORTS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercise solutions of the given participation. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExerciseSolutions(long examId, long participantId) {
    String url = Route.SOLUTIONS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise solution with the given id of the given participation.
   * It is the responsibility of the caller to provide valid ids! It also adds the
   * {@link QueryParameter} "BROWSE_SOLUTIONS" if it's not null.
   */
  public static String toExerciseSolution(long examId, long participantId, long solutionId,
      BrowseSolutionsMode browseSolutionsMode) {
    String url = Route.SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    if (browseSolutionsMode != null) {
      url = RouteBuilder.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
          browseSolutionsMode.toString());
    }
    return url;
  }

  /**
   * Returns the absolute url to the exercise solution with the given id. It also adds the
   * {@link QueryParameter} "BROWSE_SOLUTIONS" and the url parameter "query-next/" which directly
   * redirects to the next unprocessed exercise solution when called.
   *
   * @throws InvalidParameterException
   *           when the browseSolutionsMode is null.
   */
  public static String toQueryNextSolution(long examId, long participantId, long solutionId,
      BrowseSolutionsMode browseSolutionsMode) throws InvalidParameterException {
    ValidationHelper.checkForNullValue(browseSolutionsMode, LOGGER);
    String url = Route.QUERY_NEXT_SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    url = RouteBuilder.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
        browseSolutionsMode.toString());
    return url;
  }

  /**
   * Returns the absolute url to the participation with the given id. It also adds the url parameter
   * "query-by-participation/" which directly redirects to the first unprocessed exercise solution
   * of this participation when called.
   */
  public static String toQueryFirstSolutionByParticipation(long examId, long participationId) {
    String url = Route.QUERY_FIRST_SOLUTION_BY_PARTICIPATION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participationId);
    return url;
  }

  /**
   * Returns the absolute url to the exam with the given id. It also adds the url parameter
   * "query-by-participations/" which directly redirects to the first unprocessed exercise solution
   * of the first possible participation when called.
   */
  public static String toQueryFirstSolutionByParticipations(long examId) {
    String url = Route.QUERY_FIRST_SOLUTION_BY_PARTICIPATIONS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise with the given id. It also adds the url parameter
   * "query-by-exercise/" which directly redirects to the first unprocessed exercise solution of
   * this exercise when called.
   */
  public static String toQueryFirstSolutionByExercise(long examId, long exerciseId) {
    String url = Route.QUERY_FIRST_SOLUTION_BY_EXERCISE.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.EXERCISE_ID, exerciseId);
    return url;
  }

  /**
   * Returns the absolute url to the exam with the given id. It also adds the url parameter
   * "query-by-exercises/" which directly redirects to the first unprocessed exercise solution of
   * the first possible exercise when called.
   */
  public static String toQueryFirstSolutionByExercises(long examId) {
    String url = Route.QUERY_FIRST_SOLUTION_BY_EXERCISES.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercise gradings of the given exercise solution. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExerciseGradings(long examId, long participantId, long solutionId) {
    String url = Route.GRADINGS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise grading with the given id. It is the responsibility of
   * the caller to provide valid ids! It also adds the {@link QueryParameter} "BROWSE_SOLUTIONS" if
   * it's not null.
   */
  public static String toExerciseGrading(long examId, long participantId, long solutionId,
      long gradingId, BrowseSolutionsMode browseSolutionsMode) {
    String url = Route.GRADING.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    url = replace(url, UrlParameter.GRADING_ID, gradingId);
    if (browseSolutionsMode != null) {
      url = RouteBuilder.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
          browseSolutionsMode.toString());
    }
    return url;
  }

  private static String replace(String url, UrlParameter param, Object replacement) {
    return url.replaceFirst(param.toUrl(), replacement == null ? "" : replacement.toString());
  }

  /**
   * @param url
   *          the given url
   * @param key
   *          the key of the query parameter to append
   * @param value
   *          the value of the query parameter to append
   * @return the given url appended by the new query parameter (url?key=value). If the url already
   *         contains parameters the new one will be appended at the end. If the parameter already
   *         exists in the url it will be appended also (no replacement).
   */
  public static String addQueryParameter(String url, String key, String value) {
    char delimiter = '?';
    if (url.indexOf(delimiter) != -1) {
      delimiter = '&';
    }
    return url + delimiter + key + '=' + value;
  }

}
