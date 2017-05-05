package ch.examibur.domain.aggregation;

public final class PassedParticipationComparison {

  /**
   * It is perfectly fine to suppress the unused warning cause this object is only used for JSON
   * serialization with GSON.
   */
  private final double percentageOfSuccessfulParticipations;
  @SuppressWarnings("unused")
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
    percentageOfSuccessfulParticipations = (double) numberOfSuccessfulParticipations
        / totalParticipations * 100;
    percentageOfUnsuccessfulParticipations = 100 - percentageOfSuccessfulParticipations;
  }

}
