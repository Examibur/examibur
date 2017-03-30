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
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
