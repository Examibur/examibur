package ch.examibur.ui.app.routing;

public final class RouteBuilder {

  private RouteBuilder() {
  }

  /**
   * Returns the absolute url to the dashboard.
   */
  public static String toDashboard() {
    return Route.ROOT.url();
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
   * responsibility of the caller to provide a valid ids!
   */
  public static String toExercise(long examId, long exerciseId) {
    String url = Route.EXERCISE.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.EXERCISE_ID, exerciseId);
    return url;
  }

  /**
   * Returns the absolute url to all participants of the given exam. It is the responsibility of the
   * caller to provide a valid ids!
   */
  public static String toExamParticipations(long examId) {
    String url = Route.PARTICIPANTS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    return url;
  }

  /**
   * Returns the absolute url to the participants with the given id of the given exam. It is the
   * responsibility of the caller to provide a valid ids!
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
   * responsibility of the caller to provide a valid ids!
   */
  public static String toExerciseSolutions(long examId, long participantId) {
    String url = Route.SOLUTIONS.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    return url;
  }

  /**
   * Returns the absolute url to the exercise soltion with the given id of the given participation.
   * It is the responsibility of the caller to provide a valid ids!
   */
  public static String toExerciseSolution(long examId, long participantId, long solutionId) {
    String url = Route.SOLUTION.url();
    url = replace(url, UrlParameter.EXAM_ID, examId);
    url = replace(url, UrlParameter.PARTICIPANT_ID, participantId);
    url = replace(url, UrlParameter.SOLUTION_ID, solutionId);
    return url;
  }

  private static String replace(String url, UrlParameter param, Object replacement) {
    return url.replaceFirst(param.toUrl(), replacement == null ? "" : replacement.toString());
  }

  public static String addQueryParameter(String url, String key, String value) {
    char delimiter = '?';
    if (url.indexOf(delimiter) != -1) {
      delimiter = '&';
    }
    return url + delimiter + key + "=" + value;
  }
}
