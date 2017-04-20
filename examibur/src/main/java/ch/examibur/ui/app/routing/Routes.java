package ch.examibur.ui.app.routing;

public enum Routes {
  ROOT("/"),
  EXAMS(ROOT, "exams/"),
  EXAM(EXAMS, UrlParameter.EXAM_ID),
  EXERCISES(EXAM, "exercises/"),
  EXERCISE(EXERCISES, UrlParameter.EXERCISE_ID),
  PARTICIPANTS(EXAM, "participants/"),
  PARTICIPANT(PARTICIPANTS, UrlParameter.PARTICIPANT_ID),
  SOLUTIONS(PARTICIPANT, "solutions/"),
  SOLUTION(SOLUTIONS, UrlParameter.SOLUTION_ID),;

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

  public PartialUrl with(UrlParameter parameter, Object replacement) {
    return new PartialUrl(url, parameter, replacement);
  }

  public String url() {
    return url;
  }

}
