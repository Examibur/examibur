package ch.examibur.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exercisesolution_t")
public class ExerciseSolution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exerciseSolutionId")
  private long id;

  @Column(nullable = false)
  private boolean isDone;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "exercisesolution_participantsolutionId")
  private Solution participantSolution;

  @ManyToOne
  @JoinColumn(name = "exercisesolution_exerciseId", nullable = false)
  private Exercise exercise;

  @ManyToOne
  @JoinColumn(name = "exercisesolution_participationId", nullable = false)
  private ExamParticipation participation;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }

  public Solution getParticipantSolution() {
    return participantSolution;
  }

  public void setParticipantSolution(Solution participantSolution) {
    this.participantSolution = participantSolution;
  }

  public Exercise getExercise() {
    return exercise;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  public ExamParticipation getParticipation() {
    return participation;
  }

  public void setParticipation(ExamParticipation participation) {
    this.participation = participation;
  }

}
