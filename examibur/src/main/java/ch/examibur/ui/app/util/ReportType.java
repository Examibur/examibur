package ch.examibur.ui.app.util;

public enum ReportType {
  
  /**
   * These values are used in query parameters.
   * Because of this, the type should be in lower-case.
   */
  PASSED_PARTICIPATION_COMPARISON_REPORT("passedparticipationcomparisonreport");
  
  private final String type;

  private ReportType(String type) {
    this.type = type;
  }
  
  public String type() {
    return type;
  }

}
