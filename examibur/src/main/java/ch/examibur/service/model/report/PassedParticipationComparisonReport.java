package ch.examibur.service.model.report;

public final class PassedParticipationComparisonReport {
  
  /**
   * It is perfectly fine to suppress the unused warning cause this object
   * is only used for JSON serialization with GSON.
   */
  private final double percentageOfSuccessfulParticipations;
  @SuppressWarnings("unused")
  private final double percentageOfUnsuccessfulParticipations;
  
  public PassedParticipationComparisonReport(int numberOfSuccessfulParticipations, int totalParticipations) {
    percentageOfSuccessfulParticipations = (double) numberOfSuccessfulParticipations / totalParticipations * 100;
    percentageOfUnsuccessfulParticipations = 100 - percentageOfSuccessfulParticipations;
  }

}
