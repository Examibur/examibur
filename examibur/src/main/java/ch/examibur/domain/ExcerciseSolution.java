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
@Table(name = "excercisesolution_t")
public class ExcerciseSolution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "excerciseSolutionId")
  private long id;

  @Column(nullable = false)
  private boolean isDone;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "excercisesolution_participantsolutionId")
  private Solution participantSolution;

  @ManyToOne
  @JoinColumn(name = "excercisesolution_excerciseId", nullable = false)
  private Excercise excercise;

  @ManyToOne
  @JoinColumn(name = "excercisesolution_participationId", nullable = false)
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

  public Excercise getExcercise() {
    return excercise;
  }

  public void setExcercise(Excercise excercise) {
    this.excercise = excercise;
  }

  public ExamParticipation getParticipation() {
    return participation;
  }

  public void setParticipation(ExamParticipation participation) {
    this.participation = participation;
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
    ExcerciseSolution other = (ExcerciseSolution) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
