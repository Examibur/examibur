package ch.examibur.service.model;

import java.text.DecimalFormat;

public final class ExamParticipantOverview extends AbstractParticipantOverview {

  private double progress;

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
