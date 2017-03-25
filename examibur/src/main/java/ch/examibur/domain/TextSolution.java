package ch.examibur.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "textsolution_t")
public class TextSolution extends Solution {
  
  private String solutionText;

  public String getSolutionText() {
    return solutionText;
  }

  public void setSolutionText(String solutionText) {
    this.solutionText = solutionText;
  }
  
}
