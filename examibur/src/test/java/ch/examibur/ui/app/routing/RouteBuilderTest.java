package ch.examibur.ui.app.routing;

import ch.examibur.domain.aggregation.BrowseSolutionsMode;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.ui.app.url.Link;
import org.junit.Assert;
import org.junit.Test;

public class RouteBuilderTest {

  @Test
  public void testDashboardRoute() {
    Assert.assertEquals("/", Link.toDashboard());
  }

  @Test
  public void testExamsRoute() {
    Assert.assertEquals("/exams/", Link.toExams());
  }

  @Test
  public void testExamRoute() {
    Assert.assertEquals("/exams/0/", Link.toExam(0));
  }

  @Test
  public void testExamParticipationsRoute() {
    Assert.assertEquals("/exams/1/participants/", Link.toExamParticipations(1));
  }

  @Test
  public void testExamParticipationRoute() {
    Assert.assertEquals("/exams/1/participants/2/", Link.toExamParticipation(1, 2));
  }

  @Test
  public void testExerciseRoute() {
    Assert.assertEquals("/exams/1/exercises/2/", Link.toExercise(1, 2));
  }

  @Test
  public void testExercisesRoute() {
    Assert.assertEquals("/exams/1/exercises/", Link.toExercises(1));
  }

  @Test
  public void testExerciseSolutionsRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/", Link.toExerciseSolutions(1, 2));
  }

  @Test
  public void testExerciseSolutionRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/",
        Link.toExerciseSolution(1, 2, 3, null));
  }

  @Test
  public void testExerciseSolutionRouteWithNegativeValues() {
    // negative values are not checked! It's the responisibility of the caller
    // call the RouteBuilder with valid ids.
    Assert.assertEquals("/exams/-1/participants/-2/solutions/-3/",
        Link.toExerciseSolution(-1, -2, -3, null));
  }

  @Test
  public void testExerciseSolutionRouteWithQueryParameterBrowseSolutions() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/?browse=exercise",
        Link.toExerciseSolution(1, 2, 3, BrowseSolutionsMode.BY_EXERCISE));
  }

  @Test
  public void testQueryNextSolutionRoute() throws InvalidParameterException {
    Assert.assertEquals("/exams/8/participants/17/solutions/51/query-next/?browse=exercise",
        Link.toQueryNextSolution(8, 17, 51, BrowseSolutionsMode.BY_EXERCISE));
  }

  @Test
  public void testQueryFirstSolutionByParticipationRoute() throws InvalidParameterException {
    Assert.assertEquals("/exams/8/participants/17/query-by-participation/",
        Link.toQueryFirstSolutionByParticipation(8, 17));
  }

  @Test
  public void testQueryFirstSolutionByParticipationsRoute() throws InvalidParameterException {
    Assert.assertEquals("/exams/8/query-by-participations/",
        Link.toQueryFirstSolutionByParticipations(8));
  }

  @Test
  public void testQueryFirstSolutionByExerciseRoute() throws InvalidParameterException {
    Assert.assertEquals("/exams/8/exercises/16/query-by-exercise/",
        Link.toQueryFirstSolutionByExercise(8, 16));
  }

  @Test
  public void testQueryFirstSolutionByExercisesRoute() throws InvalidParameterException {
    Assert.assertEquals("/exams/8/query-by-exercises/", Link.toQueryFirstSolutionByExercises(8));
  }

  @Test
  public void testExerciseGradingsRoute() {
    Assert.assertEquals("/exams/1/participants/2/solutions/3/gradings/",
        Link.toExerciseGradings(1, 2, 3));
  }

  @Test
  public void testAddQueryParameter() {
    Assert.assertEquals("/exams/8/participants/18/solutions/54/?browse=participations", Link
        .addQueryParameter("/exams/8/participants/18/solutions/54/", "browse", "participations"));
  }

  @Test
  public void testAddQueryParameterWithMultipleParameters() {
    Assert.assertEquals("/exams/8/participants/18/solutions/54/?browse=participations&querynext=",
        Link.addQueryParameter("/exams/8/participants/18/solutions/54/?browse=participations",
            "querynext", ""));
  }

}
