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

}
