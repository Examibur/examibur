package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.examparticipation.ExamParticipationService;
import ch.examibur.ui.app.util.Renderer;

import com.google.inject.Inject;

import java.util.Map;

import spark.Request;
import spark.Response;

public class ExamParticipationController implements Controller {

  public static final String PARAM_PARTICIPANT_ID = ":participantId";

  private final ExamService examService;
  private final ExamParticipationService examParticipationService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   * @param examParticipationService
   *          the service to access exam participations
   */
  @Inject
  public ExamParticipationController(Renderer engine, ExamService examService, 
      ExamParticipationService examParticipationService) {
    this.engine = engine;
    this.examService = examService;
    this.examParticipationService = examParticipationService;
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
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "examParticipationTabExerciseView.ftl");
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
    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));

    Map<String, Object> model = request.attribute(MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("participantsOverview", examParticipationService.getExamParticipantsOverview(examId));
    return engine.render(model, "examParticipationsTab.ftl");
  }

}
