package ch.examibur.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "examparticipation_t")
public class ExamParticipation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "examparticipationId")
  private long id;

  @Column(nullable = false)
  private Date participationDate;

  private String pseudonym;

  @ManyToOne
  @JoinColumn(name = "examparticipation_participantId", nullable = false)
  private User participant;

  @ManyToOne
  @JoinColumn(name = "examparticipation_exam", nullable = false)
  private Exam exam;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getparticipationDate() {
    return participationDate;
  }

  public void setparticipationDate(Date participationDate) {
    this.participationDate = participationDate;
  }

  public String getPseudonym() {
    return pseudonym;
  }

  public void setPseudonym(String pseudonym) {
    this.pseudonym = pseudonym;
  }

  public User getParticipant() {
    return participant;
  }

  public void setParticipant(User participant) {
    this.participant = participant;
  }

  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((exam == null) ? 0 : exam.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((participant == null) ? 0 : participant.hashCode());
    result = prime * result + ((participationDate == null) ? 0 : participationDate.hashCode());
    result = prime * result + ((pseudonym == null) ? 0 : pseudonym.hashCode());
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
    ExamParticipation other = (ExamParticipation) obj;
    if (exam == null) {
      if (other.exam != null) {
        return false;
      }
    } else if (!exam.equals(other.exam)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (participant == null) {
      if (other.participant != null) {
        return false;
      }
    } else if (!participant.equals(other.participant)) {
      return false;
    }
    if (participationDate == null) {
      if (other.participationDate != null) {
        return false;
      }
    } else if (!participationDate.equals(other.participationDate)) {
      return false;
    }
    if (pseudonym == null) {
      if (other.pseudonym != null) {
        return false;
      }
    } else if (!pseudonym.equals(other.pseudonym)) {
      return false;
    }
    return true;
  }

}
