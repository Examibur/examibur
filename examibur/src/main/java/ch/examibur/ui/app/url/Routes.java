package ch.examibur.ui.app.url;

public enum Routes {
  ROOT("/"),
  LOGIN(ROOT, "login/"),
  LOGOUT(ROOT, "logout/"),
  EXAMS(ROOT, "exams/"),
  EXAM(EXAMS, UrlParameter.EXAM_ID),
  EXERCISES(EXAM, "exercises/"),
  EXERCISE(EXERCISES, UrlParameter.EXERCISE_ID),
  EXERCISE_PARTICIPANTS(EXERCISE, "participants/"),
  PARTICIPANTS(EXAM, "participants/"),
  REPORTS(EXAM, "reports/"),
  REPORTS_JSON(REPORTS, "json/"),
  PARTICIPANT(PARTICIPANTS, UrlParameter.PARTICIPANT_ID),
  SOLUTIONS(PARTICIPANT, "solutions/"),
  SOLUTION(SOLUTIONS, UrlParameter.SOLUTION_ID),
  GRADINGS(SOLUTION, "gradings/"),
  GRADING(GRADINGS, UrlParameter.GRADING_ID),
  QUERY_FIRST_SOLUTION_BY_PARTICIPATION(PARTICIPANT, "query-by-participation/"),
  QUERY_FIRST_SOLUTION_BY_PARTICIPATIONS(EXAM, "query-by-participations/"),
  QUERY_FIRST_SOLUTION_BY_EXERCISE(EXERCISE, "query-by-exercise/"),
  QUERY_FIRST_SOLUTION_BY_EXERCISES(EXAM, "query-by-exercises/"),
  QUERY_NEXT_SOLUTION(SOLUTION, "query-next/");

  private final String url;

  private Routes(String url) {
    this.url = url;
  }

  private Routes(Routes paramRoute, String url) {
    this.url = paramRoute.url + url;
  }

  private Routes(Routes paramRoute, UrlParameter urlParameter) {
    this.url = paramRoute.url + urlParameter.toUrl() + '/';
  }

  public String url() {
    return url;
  }

}
