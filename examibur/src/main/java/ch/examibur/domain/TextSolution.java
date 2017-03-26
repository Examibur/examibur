package ch.examibur.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class TextSolution extends Solution {
  
  private String solutionText;

  public String getSolutionText() {
    return solutionText;
  }

  public void setSolutionText(String solutionText) {
    this.solutionText = solutionText;
  }
  
}
