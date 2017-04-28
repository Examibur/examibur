package ch.examibur.ui.app.controller;

import ch.examibur.service.ExamService;
import ch.examibur.service.ExerciseService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public final class ExamReportController implements Controller {
  
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
  public ExamReportController(Renderer engine, ExamService examService, ExerciseService exerciseService) {
    this.engine = engine;
    this.examService = examService;
    this.exerciseService = exerciseService;
  }
  
  /**
   * Returns a list of exercises.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if a parameter is invalid. throws
   *           {@link AuthorizationException} if the user is not authorized to display the exam or
   *           exercises. throws {@link NotFoundException} if the exam/exercises is/are not found.
   *           throws {@link CommunicationException} if an exception during the communication occurs
   */
  public String displayReports(Request request, Response response) throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    model.put("exam", examService.getExam(examId));

    return engine.render(model, "examReportsTab.ftl");
  }
  
  /**
   * Adds breadcrumb for `reports/`.
   * 
   * @throws InvalidParameterException
   *           if the examid parameter is not set or < 0.
   */
  public void addBreadCrumb(Request request, Response response) throws InvalidParameterException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);
    RequestHelper.pushBreadCrumb(request, "Reports", RouteBuilder.toReports(examId));
  }

}
