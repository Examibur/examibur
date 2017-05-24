package ch.examibur.ui.app.routing;

import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.ui.app.model.MessageType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
      BrowseSolutionsValue browseSolutionsValue) {
    String url = Route.SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    if (browseSolutionsValue != null) {
      url = RouteBuilder.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS,
          browseSolutionsValue.toString());
    }
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
      long gradingId, BrowseSolutionsValue browseSolutionsValue) {
    String url = Route.GRADING.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    url = replace(url, UrlParameter.GRADING_ID, gradingId);
    if (browseSolutionsValue != null) {
      url = RouteBuilder.addQueryParameter(url, QueryParameter.BROWSE_SOLUTIONS,
          browseSolutionsValue.toString());
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
      LOGGER.error(e.getMessage());
      IllegalOperationException illegalOperationException =
          new IllegalOperationException("Error while encoding the query parameter "
              + QueryParameter.NOTIFICATION_MESSAGE.toString());
      LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
      throw illegalOperationException;
    }
    String target = url;
    target =
        RouteBuilder.addQueryParameter(target, QueryParameter.NOTIFICATION_MESSAGE, encodedMessage);
    target =
        RouteBuilder.addQueryParameter(target, QueryParameter.NOTIFICATION_TYPE, type.getType());
    return target;
  }

}
