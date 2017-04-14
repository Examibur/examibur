package ch.examibur.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class TextSolution extends Solution {

  @Column(nullable = false)
  private String solutionText;

  public String getSolutionText() {
    return solutionText;
  }

  public void setSolutionText(String solutionText) {
    this.solutionText = solutionText;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((solutionText == null) ? 0 : solutionText.hashCode());
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
    TextSolution other = (TextSolution) obj;
    if (solutionText == null) {
      if (other.solutionText != null) {
        return false;
      }
    } else if (!solutionText.equals(other.solutionText)) {
      return false;
    }
    return true;
  }

}
