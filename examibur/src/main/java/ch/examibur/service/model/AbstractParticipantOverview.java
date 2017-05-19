package ch.examibur.service.model;

import java.util.Optional;

public abstract class AbstractParticipantOverview {

  private long id;
  private String pseudonym;
  private double totalPoints;
  private Optional<Double> grading;
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPseudonym() {
    return pseudonym;
  }

  public void setPseudonym(String pseudonym) {
    this.pseudonym = pseudonym;
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
  
}
