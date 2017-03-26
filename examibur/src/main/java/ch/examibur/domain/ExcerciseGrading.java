package ch.examibur.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "excercisegrading_t")
public class ExcerciseGrading {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long excerciseGradingId;

  private Date creationDate;

  private String comment;

  private String reasoning;

  private double points;

  @Enumerated(EnumType.STRING)
  private ExamState createdInState;

  private boolean isFinalGrading;

  @ManyToOne
  @JoinColumn(name = "excercisegrading_authorId")
  private User gradingAuthor;

  @ManyToOne
  @JoinColumn(name = "excercisegrading_excercisesolutionId")
  private ExcerciseSolution excerciseSolution;

  
  public long getExcerciseGradingId() {
    return excerciseGradingId;
  }

  public void setExcerciseGradingId(long excerciseGradingId) {
    this.excerciseGradingId = excerciseGradingId;
  }

  public Date getcreationDate() {
    return creationDate;
  }

  public void setcreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getReasoning() {
    return reasoning;
  }

  public void setReasoning(String reasoning) {
    this.reasoning = reasoning;
  }

  public double getPoints() {
    return points;
  }

  public void setPoints(double points) {
    this.points = points;
  }

  public ExamState getCreatedInState() {
    return createdInState;
  }

  public void setCreatedInState(ExamState createdInState) {
    this.createdInState = createdInState;
  }

  public boolean isFinalGrading() {
    return isFinalGrading;
  }

  public void setFinalGrading(boolean isFinalGrading) {
    this.isFinalGrading = isFinalGrading;
  }

  public User getGradingAuthor() {
    return gradingAuthor;
  }

  public void setGradingAuthor(User gradingAuthor) {
    this.gradingAuthor = gradingAuthor;
  }

  public ExcerciseSolution getExcerciseSolution() {
    return excerciseSolution;
  }

  public void setExcerciseSolution(ExcerciseSolution excerciseSolution) {
    this.excerciseSolution = excerciseSolution;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (excerciseGradingId ^ (excerciseGradingId >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ExcerciseGrading other = (ExcerciseGrading) obj;
    if (excerciseGradingId != other.excerciseGradingId) {
      return false;
    }
    return true;
  }

}
