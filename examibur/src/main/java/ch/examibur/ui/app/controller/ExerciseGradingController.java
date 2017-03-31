package ch.examibur.ui.app.controller;

import static spark.Spark.post;

import spark.Request;
import spark.Response;

public class ExerciseGradingController extends Controller {

  public ExerciseGradingController(Controller preController) {
    super(preController, "/gradings");
  }

  /**
   * Adds a new grading or review to a specific solution and redirects back to
   * that solution.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return nothing to return
   */
  public String addExerciseGrading(Request request, Response response) {
    response.redirect("/exams/" + request.params(ExamController.PARAM_EXAM_ID) + "/participants/"
        + request.params(ExamParticipationController.PARAM_PARTICIPANT_ID) + "/solutions/"
        + request.params(ExerciseSolutionController.PARAM_SOLUTION_ID) + "/", 302);
    return null;
  }

  @Override
  public void route() {
    post("/", this::addExerciseGrading);
  }

}