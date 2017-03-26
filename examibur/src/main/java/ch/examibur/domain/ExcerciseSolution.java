package ch.examibur.domain;

import javax.persistence.CascadeType;
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
  private long excerciseSolutionId;

  private boolean isDone;

  @OneToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "excercisesolution_participantsolutionId")
  private Solution participantSolution;

  @ManyToOne
  @JoinColumn(name = "excercisesolution_excerciseId")
  private Excercise excercise;

  @ManyToOne
  @JoinColumn(name = "excercisesolution_participationId")
  private ExamParticipation participation;
  

  public long getExcerciseSolutionId() {
    return excerciseSolutionId;
  }

  public void setExcerciseSolutionId(long excerciseSolutionId) {
    this.excerciseSolutionId = excerciseSolutionId;
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (excerciseSolutionId ^ (excerciseSolutionId >>> 32));
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
    ExcerciseSolution other = (ExcerciseSolution) obj;
    if (excerciseSolutionId != other.excerciseSolutionId) {
      return false;
    }
    return true;
  }

}
