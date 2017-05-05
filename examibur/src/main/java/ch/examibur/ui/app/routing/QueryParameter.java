package ch.examibur.ui.app.routing;

public enum QueryParameter {
  Ref("ref");

  private final String name;

  private QueryParameter(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

}
