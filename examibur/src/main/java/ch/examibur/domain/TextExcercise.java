package ch.examibur.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class TextExcercise extends Excercise {
  
  @Column(nullable = false)
  private String taskDescription; 

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }
  
}
