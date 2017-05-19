package ch.examibur.service.model;

public final class ExerciseParticipantOverview extends AbstractParticipantOverview {

  private boolean isDone;

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

}
