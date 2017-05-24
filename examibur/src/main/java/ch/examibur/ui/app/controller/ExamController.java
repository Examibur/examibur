package ch.examibur.ui.app.controller;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.User;
import ch.examibur.service.ExamService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.model.MessageType;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Redirect;
import spark.Request;
import spark.Response;

public class ExamController implements Controller {

  private final ExamService examService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   */
  @Inject
  public ExamController(Renderer engine, ExamService examService) {
    this.engine = engine;
    this.examService = examService;
  }

  /**
   * Returns a specific exam.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if a parameter is invalid. throws
   *           {@link AuthorizationException} if the user is not authorized to display this exam.
   *           throws {@link NotFoundException} if the exam is not found. throws
   *           {@link CommunicationException} if an exception during the communication occurs
   */

  public String displayExam(Request request, Response response) throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("maxPoints", examService.getMaxPoints(examId));
    return engine.render(model, "views/examInformationTab.ftlh");
  }

  /**
   * Returns a list of exams.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws ExamiburException
   *           throws {@link CommunicationException} if an exception during the communication
   *           occurs. throws {@link AuthorizationException} if the user is not authorized. throws
   *           {@link InvalidParameterException} if userid < 0
   */
  public String listExams(Request request, Response response) throws ExamiburException {
    User user = request.attribute(RequestAttributes.USER);
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exams", examService.getExamsForAuthor(user.getId()));
    return engine.render(model, "views/examsView.ftlh");
  }

  /**
   * Updates the {@link ExamState} of the {@link Exam} according to the following transition logic:
   * CORRECTION -> REVIEW -> APPROVAL -> APPEAL -> ARCHIVED
   * 
   * @return nothing to return. It redirects back to the Exam.
   */
  public String setNextExamState(Request request, Response response) throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    String target = RouteBuilder.toExam(examId);
    try {
      examService.setNextState(examId);
      target = RouteBuilder.addMessageParameter(target, "Prüfungsstatus erfolgreich aktualisiert!",
          MessageType.SUCCESS);
    } catch (IllegalOperationException e) {
      target = RouteBuilder.addMessageParameter(target,
          "Fehler: Diese Prüfung hat noch unbearbeitete Aufgaben.", MessageType.DANGER);
    }
    response.redirect(target, Redirect.Status.FOUND.intValue());
    return null;
  }

  /**
   * Adds breadcurmb for `/exams`.
   */
  public void addBreadCrumb(Request request, Response response) {
    RequestHelper.pushBreadCrumb(request, "Prüfungen", RouteBuilder.toExams());
  }

  /**
   * Adds breadcurmb for `/exams/:examid`.
   * 
   * @throws InvalidParameterException
   *           if the examid parameter is not set or < 0.
   */
  public void addSpecificBreadCrumb(Request request, Response response)
      throws InvalidParameterException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Prüfung #" + examId, RouteBuilder.toExam(examId));
  }
}
