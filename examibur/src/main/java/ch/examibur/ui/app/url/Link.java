package ch.examibur.ui.app.url;

import ch.examibur.domain.aggregation.BrowseSolutionsMode;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.ui.app.model.MessageType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Link {

  private static final Logger LOGGER = LoggerFactory.getLogger(Link.class);

  private Link() {
  }

  /**
   * Returns the absolute url to the dashboard.
   */
  public static String toDashboard() {
    return Routes.ROOT.url();
  }

  /**
   * Returns the absolute url to the login page.
   */
  public static String toLogin() {
    return Routes.LOGIN.url();
  }

  /**
   * Returns the absolute url to the logout page.
   */
  public static String toLogout() {
    return Routes.LOGOUT.url();
  }

  /**
   * Returns the absolute url to the list of exams.
   */
  public static String toExams() {
    return Routes.EXAMS.url();
  }

  /**
   * Returns the absolute url to the exam with the given id. It is the responsibility of the caller
   * to provide a valid id!
   */
  public static String toExam(long examId) {
    String url = Routes.EXAM.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercises of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toExercises(long examId) {
    String url = Routes.EXERCISES.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise with the given id of the given exam. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExercise(long examId, long exerciseId) {
    String url = Routes.EXERCISE.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.EXERCISE_ID, exerciseId);
    return url;
  }

  /**
   * Returns the absolute url to all participants of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toExamParticipations(long examId) {
    String url = Routes.PARTICIPANTS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the participants with the given id of the given exam. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExamParticipation(long examId, long participantId) {
    String url = Routes.PARTICIPANT.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    return url;
  }

  /**
   * Returns the absolute url to all reports of the given exam. It is the responsibility of the
   * caller to provide a valid id!
   */
  public static String toReports(long examId) {
    String url = Routes.REPORTS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercise solutions of the given participation. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExerciseSolutions(long examId, long participantId) {
    String url = Routes.SOLUTIONS.url();
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
    String url = Routes.SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    if (browseSolutionsMode != null) {
      url = Link.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
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

    if (browseSolutionsMode == null) {
      InvalidParameterException invalidParameterException =
          new InvalidParameterException("BrowseSolutionsMode parameter is null");
      LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }

    String url = Routes.QUERY_NEXT_SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    url = Link.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
        browseSolutionsMode.toString());
    return url;
  }

  /**
   * Returns the absolute url to the participation with the given id. It also adds the url parameter
   * "query-by-participation/" which directly redirects to the first unprocessed exercise solution
   * of this participation when called.
   */
  public static String toQueryFirstSolutionByParticipation(long examId, long participationId) {
    String url = Routes.QUERY_FIRST_SOLUTION_BY_PARTICIPATION.url();
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
    String url = Routes.QUERY_FIRST_SOLUTION_BY_PARTICIPATIONS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise with the given id. It also adds the url parameter
   * "query-by-exercise/" which directly redirects to the first unprocessed exercise solution of
   * this exercise when called.
   */
  public static String toQueryFirstSolutionByExercise(long examId, long exerciseId) {
    String url = Routes.QUERY_FIRST_SOLUTION_BY_EXERCISE.url();
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
    String url = Routes.QUERY_FIRST_SOLUTION_BY_EXERCISES.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to all exercise gradings of the given exercise solution. It is the
   * responsibility of the caller to provide valid ids!
   */
  public static String toExerciseGradings(long examId, long participantId, long solutionId) {
    String url = Routes.GRADINGS.url();
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
    String url = Routes.GRADING.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    url = replace(url, UrlParameter.GRADING_ID, gradingId);
    if (browseSolutionsMode != null) {
      url = Link.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS.toString(),
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

  /**
   * A convenience method, equivalent to {@link #addQueryParameter(String, String, String)} with a
   * {@link QueryParameter} input for predefined query parameters.
   */
  public static String addQueryParameter(String url, QueryParameter queryParameter, String value) {
    return addQueryParameter(url, queryParameter.toString(), value);
  }

  /**
   * @param url
   *          the given url
   * @param message
   *          the notification message text
   * @param type
   *          the type of the message
   * @return the given url appended by the message (UTF-8 encoded) and its key as query parameters.
   *         If a message already exists in the url it will be appended also (no replacement).
   * @throws ExamiburException
   *           throws an {@link IllegalOperationException} if the url encoding fails.
   */
  public static String addMessageParameter(String url, String message, MessageType type)
      throws ExamiburException {
    String encodedMessage;
    try {
      encodedMessage = URLEncoder.encode(message, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("Error while encoding the query parameter", e);
      IllegalOperationException illegalOperationException =
          new IllegalOperationException("Error while encoding the query parameter "
              + QueryParameter.NOTIFICATION_MESSAGE.toString());
      LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
      throw illegalOperationException;
    }
    String target = url;
    target = Link.addQueryParameter(target, QueryParameter.NOTIFICATION_MESSAGE, encodedMessage);
    target = Link.addQueryParameter(target, QueryParameter.NOTIFICATION_TYPE, type.getType());
    return target;
  }

}
