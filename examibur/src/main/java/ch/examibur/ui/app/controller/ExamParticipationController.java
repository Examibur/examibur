package ch.examibur.ui.app.controller;

import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExamParticipationController implements Controller {

  private final Renderer engine;

  @Inject
  public ExamParticipationController(Renderer engine) {
    this.engine = engine;
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
   */
  public String listExamParticipations(Request request, Response response) {
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "examParticipationTabExerciseView.ftl");
  }

  /**
   * Adds breadcurmb for `exercises/`.
   */
  public void addBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Teilnehmer", RouteBuilder.toExamParticipations(examId));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    long participantId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.PARTICIPANT_ID);

    RequestHelper.pushBreadCrumb(request, "Teilnehmer #" + participantId,
        RouteBuilder.toExamParticipation(examId, participantId));
  }

}
