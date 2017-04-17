package ch.examibur.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
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

  @Column(nullable = false)
  private Date creationDate;

  private Date lastModified;

  @ManyToOne
  @JoinColumn(name = "exam_authorId")
  private User author;

  @ManyToOne
  @JoinColumn(name = "exam_moduleId")
  private Module module;

  private int allowedTimeInMin;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExamState state;

  @ElementCollection
  @CollectionTable(name = "allowedUtility_t")
  @Column(name = "allowedUtility")
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

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
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
    result = prime * result + allowedTimeInMin;
    result = prime * result + ((allowedUtilities == null) ? 0 : allowedUtilities.hashCode());
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
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
    if (allowedTimeInMin != other.allowedTimeInMin) {
      return false;
    }
    if (allowedUtilities == null) {
      if (other.allowedUtilities != null) {
        return false;
      }
    } else if (!allowedUtilities.equals(other.allowedUtilities)) {
      return false;
    }
    if (author == null) {
      if (other.author != null) {
        return false;
      }
    } else if (!author.equals(other.author)) {
      return false;
    }
    if (dueDate == null) {
      if (other.dueDate != null) {
        return false;
      }
    } else if (!dueDate.equals(other.dueDate)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (module == null) {
      if (other.module != null) {
        return false;
      }
    } else if (!module.equals(other.module)) {
      return false;
    }
    if (state != other.state) {
      return false;
    }
    return true;
  }
}
