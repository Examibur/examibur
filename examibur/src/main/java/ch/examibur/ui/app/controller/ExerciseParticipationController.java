package ch.examibur.ui.app.controller;

import ch.examibur.service.ExamService;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.url.Link;
import ch.examibur.ui.app.url.UrlHelpers;
import ch.examibur.ui.app.url.UrlParameter;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public final class ExerciseParticipationController implements Controller {

  private final ExamService examService;
  private final ExerciseSolutionService exerciseSolutionService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   * @param exerciseSolutionService
   *          the service to access exercise solutions
   */
  @Inject
  public ExerciseParticipationController(Renderer engine, ExamService examService,
      ExerciseSolutionService exerciseSolutionService) {
    this.engine = engine;
    this.examService = examService;
    this.exerciseSolutionService = exerciseSolutionService;
  }

  /**
   * Returns a list of exercises participations.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if a parameter is invalid. throws
   *           {@link AuthorizationException} if the user is not authorized to display the exam or
   *           exercise participations. throws {@link NotFoundException} if the exam/exercise
   *           participations is/are not found. throws {@link CommunicationException} if an
   *           exception during the communication occurs
   */
  public String listExerciseParticipations(Request request, Response response)
      throws ExamiburException {
    long examId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long exerciseId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXERCISE_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("participantsOverview",
        exerciseSolutionService.getExerciseParticipantsOverview(exerciseId));
    model.put("visibleColumns", new String[] { "testee", "points", "grade", "done" });
    return engine.render(model, "views/exerciseParticipationsTab.ftlh");
  }

  /**
   * Adds breadcrumb for `participants/`.
   * 
   * @throws InvalidParameterException
   *           if the examid parameter is not set or < 0.
   */
  public void addBreadCrumb(Request request, Response response) throws InvalidParameterException {
    long examId = UrlHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Teilnahmen", Link.toExercises(examId));
  }

}
