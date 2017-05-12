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
@Table(name = "exercise_t")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exercise_type")
public abstract class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exerciseId")
  private long id;

  private String title;

  @Column(nullable = false)
  private double maxPoints;

  @Column(nullable = false)
  private int orderInExam;

  @ManyToOne
  @JoinColumn(name = "exercise_examId", nullable = false)
  private Exam exam;

  @ManyToOne
  @JoinColumn(name = "exercise_reviewerId")
  private User reviewer;

  @ManyToOne
  @JoinColumn(name = "exercise_graderId")
  private User grader;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "exercise_exampleSolutionId")
  private Solution exampleSolution;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getMaxPoints() {
    return maxPoints;
  }

  public void setMaxPoints(double maxPoints) {
    this.maxPoints = maxPoints;
  }

  public int getOrderInExam() {
    return orderInExam;
  }

  public void setOrderInExam(int orderInExam) {
    this.orderInExam = orderInExam;
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
}
