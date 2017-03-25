package ch.examibur.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "textexcercise_t")
public class TextExcercise extends Excercise {
  private String taskDescription; 

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }
  
}
