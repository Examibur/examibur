package ch.examibur.business.model;

import ch.examibur.domain.ExamParticipation;
import java.text.DecimalFormat;

public final class ExamParticipantOverview {

  private ExamParticipation examParticipation;
  private double totalPoints;
  private double grading;
  private double progress;

  public ExamParticipation getExamParticipation() {
    return examParticipation;
  }

  public void setExamParticipation(ExamParticipation examParticipation) {
    this.examParticipation = examParticipation;
  }

  public double getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(double totalPoints) {
    this.totalPoints = totalPoints;
  }

  public double getGrading() {
    return grading;
  }

  public void setGrading(double grading) {
    this.grading = grading;
  }

  public double getProgress() {
    return progress;
  }

  public void setProgress(double progress) {
    this.progress = progress;
  }
  
  public String getFormattedProgress() {
    return new DecimalFormat("#0.00%").format(progress);
  }

}
