package ch.examibur.domain.aggregation;

public final class ExamPerformance {
  
  private final double averageGrade;
  private final double medianGrade;
  
  public ExamPerformance(double averageGrade, double medianGrade) {
    this.averageGrade = averageGrade;
    this.medianGrade = medianGrade;
  }

  public double getAverageGrade() {
    return averageGrade;
  }

  public double getMedianGrade() {
    return medianGrade;
  }

}
