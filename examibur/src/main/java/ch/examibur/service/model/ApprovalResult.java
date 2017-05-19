package ch.examibur.service.model;

public enum ApprovalResult {
  ACCEPT("accept"),
  REJECT("reject");

  private final String value;

  private ApprovalResult(String value) {
    this.value = value;
  }

  /**
   * @return an Enum of type {@link ApprovalResult} that corresponds to the string with the given
   *         input. Null if id doesn't exist.
   */
  public static ApprovalResult fromString(String input) {
    for (ApprovalResult ar : ApprovalResult.values()) {
      if (ar.value.equals(input)) {
        return ar;
      }
    }
    return null;
  }
}
