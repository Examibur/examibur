package ch.examibur.service.model;

import ch.examibur.domain.ExamParticipation;
import java.text.DecimalFormat;
import java.util.Optional;

public final class ExamParticipantOverview {

  private ExamParticipation examParticipation;
  private double totalPoints;
  private Optional<Double> grading;
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

  public Optional<Double> getGrading() {
    return grading;
  }

  public void setGrading(Optional<Double> grading) {
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
