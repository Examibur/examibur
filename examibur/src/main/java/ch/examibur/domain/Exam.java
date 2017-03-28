package ch.examibur.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name = "exam_t")
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "examId")
  private long id;

  private Date dueDate;

  @ManyToOne
  @JoinColumn(name = "exam_authorId")
  private User author;
  
  @ManyToOne
  @JoinColumn(name = "exam_moduleId")
  private Module module;

  private int allowedTimeInMin;

  @Enumerated(EnumType.STRING)
  private ExamState state;

  @ElementCollection
  private List<String> allowedUtilities = new ArrayList<>();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public int getAllowedTimeInMin() {
    return allowedTimeInMin;
  }

  public void setAllowedTimeInMin(int allowedTimeInMin) {
    this.allowedTimeInMin = allowedTimeInMin;
  }

  public ExamState getState() {
    return state;
  }

  public void setState(ExamState state) {
    this.state = state;
  }

  public List<String> getAllowedUtilities() {
    return allowedUtilities;
  }

  public void setAllowedUtilities(List<String> allowedUtilities) {
    this.allowedUtilities = allowedUtilities;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
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
    Exam other = (Exam) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }
}
