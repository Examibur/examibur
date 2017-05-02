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
@Table(name = "exercisegrading_t")
public class ExerciseGrading {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exerciseGradingId")
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
  @JoinColumn(name = "exercisegrading_authorId", nullable = false)
  private User gradingAuthor;

  @ManyToOne
  @JoinColumn(name = "exercisegrading_exercisesolutionId", nullable = false)
  private ExerciseSolution exerciseSolution;

  public ExerciseGrading() {
    // empty constructor for JPA
  }

  public ExerciseGrading(Date creationDate, String comment, String reasoning, double points,
      ExamState createdInState, boolean isFinalGrading, User gradingAuthor,
      ExerciseSolution exerciseSolution) {
    super();
    this.creationDate = creationDate;
    this.comment = comment;
    this.reasoning = reasoning;
    this.points = points;
    this.createdInState = createdInState;
    this.isFinalGrading = isFinalGrading;
    this.gradingAuthor = gradingAuthor;
    this.exerciseSolution = exerciseSolution;
  }

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

  public ExerciseSolution getExerciseSolution() {
    return exerciseSolution;
  }

  public void setExerciseSolution(ExerciseSolution exerciseSolution) {
    this.exerciseSolution = exerciseSolution;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comment == null) ? 0 : comment.hashCode());
    result = prime * result + ((createdInState == null) ? 0 : createdInState.hashCode());
    result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
    result = prime * result + ((exerciseSolution == null) ? 0 : exerciseSolution.hashCode());
    result = prime * result + ((gradingAuthor == null) ? 0 : gradingAuthor.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + (isFinalGrading ? 1231 : 1237);
    long temp;
    temp = Double.doubleToLongBits(points);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((reasoning == null) ? 0 : reasoning.hashCode());
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
    ExerciseGrading other = (ExerciseGrading) obj;
    if (comment == null) {
      if (other.comment != null) {
        return false;
      }
    } else if (!comment.equals(other.comment)) {
      return false;
    }
    if (createdInState != other.createdInState) {
      return false;
    }
    if (creationDate == null) {
      if (other.creationDate != null) {
        return false;
      }
    } else if (!creationDate.equals(other.creationDate)) {
      return false;
    }
    if (exerciseSolution == null) {
      if (other.exerciseSolution != null) {
        return false;
      }
    } else if (!exerciseSolution.equals(other.exerciseSolution)) {
      return false;
    }
    if (gradingAuthor == null) {
      if (other.gradingAuthor != null) {
        return false;
      }
    } else if (!gradingAuthor.equals(other.gradingAuthor)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (isFinalGrading != other.isFinalGrading) {
      return false;
    }
    if (Double.doubleToLongBits(points) != Double.doubleToLongBits(other.points)) {
      return false;
    }
    if (reasoning == null) {
      if (other.reasoning != null) {
        return false;
      }
    } else if (!reasoning.equals(other.reasoning)) {
      return false;
    }
    return true;
  }

}
