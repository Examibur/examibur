package ch.examibur.domain.aggregation;

public final class PassedParticipationComparison {

  private final double percentageOfSuccessfulParticipations;
  private final double percentageOfUnsuccessfulParticipations;

  /**
   * Constructor.
   * 
   * @param numberOfSuccessfulParticipations
   *          the number of successful participations
   * @param totalParticipations
   *          the total number of all participants
   */
  public PassedParticipationComparison(int numberOfSuccessfulParticipations,
      int totalParticipations) {
    percentageOfSuccessfulParticipations =
        (double) numberOfSuccessfulParticipations / totalParticipations * 100;
    percentageOfUnsuccessfulParticipations = 100 - percentageOfSuccessfulParticipations;
  }

  public double getPercentageOfSuccessfulParticipations() {
    return percentageOfSuccessfulParticipations;
  }

  public double getPercentageOfUnsuccessfulParticipations() {
    return percentageOfUnsuccessfulParticipations;
  }

}
