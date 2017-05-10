package ch.examibur.domain.aggregation;

public final class ExerciseAverageMaxPointsComparison {

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

}
