package ch.examibur.ui.app.controller;

import static spark.Spark.get;
import static spark.Spark.path;

import ch.examibur.ui.app.util.TemplateRenderer;

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;

public class ExamParticipationController extends Controller {

  public static final String PARAM_PARTICIPANT_ID = ":participantId";

  public ExamParticipationController(Controller preController) {
    super(preController, "/participants");
  }

  /**
   * Returns a specific exam participation.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String displayExamParticipation(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "examParticipationTabExerciseView.ftl");
  }

  /**
   * Returns a list of exam participations.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String listExamParticipations(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    return new TemplateRenderer().render(model, "examParticipationTabExerciseView.ftl");
  }

  @Override
  public void route() {
    ExerciseSolutionController exerciseSolutionController = new ExerciseSolutionController(this);

    get("/", this::listExamParticipations);

    path("/" + PARAM_PARTICIPANT_ID, () -> {
      get("/", this::displayExamParticipation);

      path(exerciseSolutionController.relativePath, () -> {
        exerciseSolutionController.route();
      });
    });
  }

}
