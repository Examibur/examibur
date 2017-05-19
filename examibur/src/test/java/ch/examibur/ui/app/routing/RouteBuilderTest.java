package ch.examibur.ui.app.routing;

import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import org.junit.Assert;
import org.junit.Test;

public class RouteBuilderTest {

  @Test
  public void testDashboardRoute() {
    Assert.assertEquals("/", RouteBuilder.toDashboard());
  }

  @Test
  public void testExamsRoute() {
    Assert.assertEquals("/exams/", RouteBuilder.toExams());
  }

  @Test
  public void testExamRoute() {
    Assert.assertEquals("/exams/0/", RouteBuilder.toExam(0));
  }

  @Test
  public void testExamParticipationsRoute() {
    Assert.assertEquals("/exams/1/participants/", RouteBuilder.toExamParticipations(1));
  }

  @Test
  public void testExamParticipationRoute() {
    Assert.assertEquals("/exams/1/participants/2/", RouteBuilder.toExamParticipation(1, 2));
  }

  @Test
  public void testExerciseRoute() {
    Assert.assertEquals("/exams/1/exercises/2/", RouteBuilder.toExercise(1, 2));
  }

  @Test
  public void testExercisesRoute() {
    Assert.assertEquals("/exams/1/exercises/", RouteBuilder.toExercises(1));
  }

  @Test
  public void testExerciseSolutionsRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/",
        RouteBuilder.toExerciseSolutions(1, 2));
  }

  @Test
  public void testExerciseSolutionRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/",
        RouteBuilder.toExerciseSolution(1, 2, 3, null));
  }

  @Test
  public void testExerciseSolutionRouteWithNegativeValues() {
    // negative values are not checked! It's the responisibility of the caller
    // call the RouteBuilder with valid ids.
    Assert.assertEquals("/exams/-1/participants/-2/solutions/-3/",
        RouteBuilder.toExerciseSolution(-1, -2, -3, null));
  }

  @Test
  public void testExerciseSolutionRouteWithQueryParameterBrowseSolutions() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/?browse=exercise",
        RouteBuilder.toExerciseSolution(1, 2, 3, BrowseSolutionsMode.BY_EXERCISE));
  }

  @Test
  public void testExerciseGradingsRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/gradings/",
        RouteBuilder.toExerciseGradings(1, 2, 3));
  }

  @Test
  public void testAddQueryParameter() {
    Assert.assertEquals("/exams/8/participants/18/solutions/54/?browse=participations", RouteBuilder
        .addQueryParameter("/exams/8/participants/18/solutions/54/", "browse", "participations"));
  }

  @Test
  public void testAddQueryParameterWithMultipleParameters() {
    Assert.assertEquals("/exams/8/participants/18/solutions/54/?browse=participations&querynext=",
        RouteBuilder.addQueryParameter(
            "/exams/8/participants/18/solutions/54/?browse=participations", "querynext", ""));
  }

}
