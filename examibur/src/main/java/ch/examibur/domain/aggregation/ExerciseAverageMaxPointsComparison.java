package ch.examibur.domain.aggregation;

public final class ExerciseAverageMaxPointsComparison implements Comparable<ExerciseAverageMaxPointsComparison> {

  private final String title;
  private final double maxPoints;
  private final double averagePoints;

  /**
   * Constructor.
   * 
   * @param title
   *          the exercise title
   * @param maxPoints
   *          the max points of the specific exercise
   * @param averagePoints
   *          the average achieved points
   */
  public ExerciseAverageMaxPointsComparison(String title, double maxPoints, double averagePoints) {
    this.title = title;
    this.maxPoints = maxPoints;
    this.averagePoints = averagePoints;
  }

  public String getTitle() {
    return title;
  }

  public double getMaxPoints() {
    return maxPoints;
  }

  public double getAveragePoints() {
    return averagePoints;
  }

  @Override
  public int compareTo(ExerciseAverageMaxPointsComparison ex) {
    int titleCmpr = title.compareTo(ex.getTitle());
    if (titleCmpr != 0) {
      return titleCmpr;
    }
    int maxPointCmpr = Double.compare(maxPoints, ex.getMaxPoints());
    if (maxPointCmpr != 0) {
      return maxPointCmpr;
    }
    int averagePointsCmpr = Double.compare(averagePoints, ex.getAveragePoints());
    if (averagePointsCmpr != 0) {
      return averagePointsCmpr;
    }
    return 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(averagePoints);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(maxPoints);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ExerciseAverageMaxPointsComparison other = (ExerciseAverageMaxPointsComparison) obj;
    if (Double.doubleToLongBits(averagePoints) != Double.doubleToLongBits(other.averagePoints))
      return false;
    if (Double.doubleToLongBits(maxPoints) != Double.doubleToLongBits(other.maxPoints))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

}
