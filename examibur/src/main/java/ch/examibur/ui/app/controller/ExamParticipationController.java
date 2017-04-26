package ch.examibur.ui.app.controller;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.examparticipation.ExamParticipationService;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExamParticipationController implements Controller {

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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
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
   * @throws IOException
   *           if an exception during the communication occurs
   * @throws AuthorizationException
   *           if the user is not authorized
   * @throws NotFoundException
   *           if the exam is not found
   */
  public String listExamParticipations(Request request, Response response)
      throws NotFoundException, AuthorizationException, IOException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("participantsOverview", examParticipationService.getExamParticipantsOverview(examId));
    return engine.render(model, "examParticipationsTab.ftl");
  }

  /**
   * Adds breadcrumb for `exercises/`.
   */
  public void addBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Teilnehmer", RouteBuilder.toExamParticipations(examId));
  }

  /**
   * Adds breadcrumb for `exercises/:exerciseId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);

    RequestHelper.pushBreadCrumb(request, "Teilnehmer #" + participantId,
        RouteBuilder.toExamParticipation(examId, participantId));
  }

}
