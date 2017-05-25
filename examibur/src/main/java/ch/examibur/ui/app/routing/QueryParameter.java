package ch.examibur.ui.app.routing;

public enum QueryParameter {
  REF("ref"),
  BROWSE_SOLUTIONS("browse"),
  NOTIFICATION_MESSAGE("notification-message"),
  NOTIFICATION_TYPE("notification-type");

  private final String name;

  private QueryParameter(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
