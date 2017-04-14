package ch.examibur.ui.app.model;

import ch.examibur.domain.ExamParticipation;

public final class ExamParticipantOverview {

  private ExamParticipation examParticipation;
  private double totalPoints;
  private double grading;

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

}
