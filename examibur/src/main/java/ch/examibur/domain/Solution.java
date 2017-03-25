package ch.examibur.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "solution_t")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Solution {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long solutionId;

  public long getSolutionId() {
    return solutionId;
  }

  public void setSolutionId(long solutionId) {
    this.solutionId = solutionId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (solutionId ^ (solutionId >>> 32));
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
    Solution other = (Solution) obj;
    if (solutionId != other.solutionId) {
      return false;
    }
    return true;
  }

}
