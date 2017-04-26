package ch.examibur.ui.app.controller;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.exercise.ExerciseService;
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

public class ExamController implements Controller {

  private final ExamService examService;
  private final ExerciseService exerciseService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   * @param exerciseService
   *          the service to access exercises
   */
  @Inject
  public ExamController(Renderer engine, ExamService examService, ExerciseService exerciseService) {
    this.engine = engine;
    this.examService = examService;
    this.exerciseService = exerciseService;
  }

  /**
   * Returns a specific exam.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws AuthorizationException
   *           if the user is not authorized to display this exam.
   * @throws NotFoundException
   *           if the exam is not found.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  public String displayExam(Request request, Response response)
      throws NotFoundException, AuthorizationException, IOException {

    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("maxPoints", exerciseService.getMaxPoints(examId));
    return engine.render(model, "examInformationTab.ftl");
  }

  /**
   * Returns a list of exams.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String listExams(Request request, Response response) {
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "404.ftl");
  }

  /**
   * Updates a specific exam and redirects to the GET controller.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return nothing to return
   */
  public String updateExam(Request request, Response response) {
    response.redirect(request.pathInfo(), 302);
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
   */
  public void addSpecificBreadCrumb(Request request, Response response) {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Prüfung #" + examId, RouteBuilder.toExam(examId));
  }
}
