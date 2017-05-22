package ch.examibur.service.model;

import ch.examibur.domain.ExamState;
import java.text.DecimalFormat;

public final class ExamParticipantOverview extends AbstractParticipantOverview {

  private double progress;
  private ExamState examState;

  public double getProgress() {
    return progress;
  }

  public void setProgress(double progress) {
    this.progress = progress;
  }

  public String getFormattedProgress() {
    return new DecimalFormat("#0.00%").format(progress);
  }

  public ExamState getExamState() {
    return examState;
  }

  public void setExamState(ExamState examState) {
    this.examState = examState;
  }

}
