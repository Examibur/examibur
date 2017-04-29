package ch.examibur.service.model.report;

public final class ExerciseAverageMaxPointsComparison {

  /**
   * It is perfectly fine to suppress the unused warning cause this object
   * is only used for JSON serialization with GSON.
   */
  @SuppressWarnings("unused")
  private final String title;
  @SuppressWarnings("unused")
  private final double maxPoints;
  @SuppressWarnings("unused")
  private final double averagePoints;

  public ExerciseAverageMaxPointsComparison(String title, double maxPoints, double averagePoints) {
    this.title = title;
    this.maxPoints = maxPoints;
    this.averagePoints = averagePoints;
  }

}
