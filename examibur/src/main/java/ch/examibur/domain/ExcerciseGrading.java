package ch.examibur.domain;

import java.sql.Date;

import javax.persistence.Column;
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
  @Column(name = "excerciseGradingId")
  private long id;

  @Column(nullable = false)
  private Date creationDate;

  private String comment;

  private String reasoning;

  @Column(nullable = false)
  private double points;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExamState createdInState;

  private boolean isFinalGrading;

  @ManyToOne
  @JoinColumn(name = "excercisegrading_authorId", nullable = false)
  private User gradingAuthor;

  @ManyToOne
  @JoinColumn(name = "excercisegrading_excercisesolutionId", nullable = false)
  private ExcerciseSolution excerciseSolution;

  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
