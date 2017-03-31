package ch.examibur.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "excercise_t")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "excercise_type")
public abstract class Excercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "excerciseId")
  private long id;

  @Column(nullable = false)
  private double maxPoints;

  @ManyToOne
  @JoinColumn(name = "excercise_examId", nullable = false)
  private Exam exam;

  @ManyToOne
  @JoinColumn(name = "excercise_reviewerId")
  private User reviewer;

  @ManyToOne
  @JoinColumn(name = "excercise_graderId")
  private User grader;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "excercise_exampleSolutionId")
  private Solution exampleSolution;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getMaxPoints() {
    return maxPoints;
  }

  public void setMaxPoints(double maxPoints) {
    this.maxPoints = maxPoints;
  }

  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }

  public User getReviewer() {
    return reviewer;
  }

  public void setReviewer(User reviewer) {
    this.reviewer = reviewer;
  }

  public User getGrader() {
    return grader;
  }

  public void setGrader(User grader) {
    this.grader = grader;
  }

  public Solution getExampleSolution() {
    return exampleSolution;
  }

  public void setExampleSolution(Solution exampleSolution) {
    this.exampleSolution = exampleSolution;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((exam == null) ? 0 : exam.hashCode());
    result = prime * result + ((exampleSolution == null) ? 0 : exampleSolution.hashCode());
    result = prime * result + ((grader == null) ? 0 : grader.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    long temp;
    temp = Double.doubleToLongBits(maxPoints);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((reviewer == null) ? 0 : reviewer.hashCode());
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
    Excercise other = (Excercise) obj;
    if (exam == null) {
      if (other.exam != null) {
        return false;
      }
    } else if (!exam.equals(other.exam)) {
      return false;
    }
    if (exampleSolution == null) {
      if (other.exampleSolution != null) {
        return false;
      }
    } else if (!exampleSolution.equals(other.exampleSolution)) {
      return false;
    }
    if (grader == null) {
      if (other.grader != null) {
        return false;
      }
    } else if (!grader.equals(other.grader)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (Double.doubleToLongBits(maxPoints) != Double.doubleToLongBits(other.maxPoints)) {
      return false;
    }
    if (reviewer == null) {
      if (other.reviewer != null) {
        return false;
      }
    } else if (!reviewer.equals(other.reviewer)) {
      return false;
    }
    return true;
  }

}
