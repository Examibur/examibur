package ch.examibur.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class TextExercise extends Exercise {

  private String title;

  @Column(nullable = false)
  private String taskDescription;

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TextExercise other = (TextExercise) obj;
    if (taskDescription == null) {
      if (other.taskDescription != null) {
        return false;
      }
    } else if (!taskDescription.equals(other.taskDescription)) {
      return false;
    }
    return true;
  }

}
