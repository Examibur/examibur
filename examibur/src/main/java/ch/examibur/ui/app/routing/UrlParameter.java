package ch.examibur.ui.app.routing;

public enum UrlParameter {
  EXAM_ID("examId"),
  EXERCISE_ID("exerciseId"),
  PARTICIPANT_ID("participantId"),
  SOLUTION_ID("solutionId");

  private final String name;

  private UrlParameter(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public String toUrl() {
    return ":" + name;
  }

}
