package ch.examibur.domain.aggregation;

public final class ExamPerformance {

  private final double averageGrade;
  private final double medianGrade;
  private final int totalParticipations;
  private final int includedParticipations;

  /**
   * Constructor to initialize all fields.
   */
  public ExamPerformance(double averageGrade, double medianGrade, int totalParticipations,
      int includedParticipations) {
    this.averageGrade = averageGrade;
    this.medianGrade = medianGrade;
    this.totalParticipations = totalParticipations;
    this.includedParticipations = includedParticipations;
  }

  public double getAverageGrade() {
    return averageGrade;
  }

  public double getMedianGrade() {
    return medianGrade;
  }

  public int getTotalParticipations() {
    return totalParticipations;
  }

  public int getIncludedParticipations() {
    return includedParticipations;
  }

}
