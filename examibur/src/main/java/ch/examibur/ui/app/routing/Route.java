package ch.examibur.ui.app.routing;

public enum Route {
  ROOT("/"),
  EXAMS(ROOT, "exams/"),
  EXAM(EXAMS, UrlParameter.EXAM_ID),
  EXERCISES(EXAM, "exercises/"),
  EXERCISE(EXERCISES, UrlParameter.EXERCISE_ID),
  PARTICIPANTS(EXAM, "participants/"),
  REPORTS(EXAM, "reports/"),
  REPORTS_JSON(REPORTS, "json/"),
  PARTICIPANT(PARTICIPANTS, UrlParameter.PARTICIPANT_ID),
  SOLUTIONS(PARTICIPANT, "solutions/"),
  SOLUTION(SOLUTIONS, UrlParameter.SOLUTION_ID),;

  private final String url;

  private Route(String url) {
    this.url = url;
  }

  private Route(Route paramRoute, String url) {
    this.url = paramRoute.url + url;
  }

  private Route(Route paramRoute, UrlParameter urlParameter) {
    this.url = paramRoute.url + urlParameter.toUrl() + '/';
  }

  String url() {
    return url;
  }

}
