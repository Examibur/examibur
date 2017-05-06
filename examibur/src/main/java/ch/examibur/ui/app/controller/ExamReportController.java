package ch.examibur.ui.app.controller;

import ch.examibur.service.ExamReportService;
import ch.examibur.service.ExamService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.routing.RoutingHelpers;
import ch.examibur.ui.app.routing.UrlParameter;
import ch.examibur.ui.app.util.ReportType;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public final class ExamReportController implements Controller {

  public static final String QUERY_PARAM_REPORT = "report";

  private final ExamService examService;
  private final ExamReportService examReportService;
  private final Renderer engine;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param examService
   *          the service to access exams
   * @param examReportService
   *          the service to access exam reports
   */
  @Inject
  public ExamReportController(Renderer engine, ExamService examService,
      ExamReportService examReportService) {
    this.engine = engine;
    this.examService = examService;
    this.examReportService = examReportService;
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
    model.put("examperformance", examReportService.getExamPerformanceReport(examId));

    return engine.render(model, "examReportsTab.ftl");
  }

  /**
   * Returns a requested report ({@link ch.examibur.ui.app.util.ReportType} as a JSON object.
   * 
   * @param request
   *          the HTTP request with the requested report type set as a query param
   * @param response
   *          the HTTP response
   * @return the report as a JSON object
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if a parameter is invalid. throws
   *           {@link AuthorizationException} if the user is not authorized to display the exam or
   *           exercises. throws {@link NotFoundException} if the exam/exam participations/exercises
   *           is/are not found. throws {@link CommunicationException} if an exception during the
   *           communication occurs
   */
  public String getReportAsJson(Request request, Response response) throws ExamiburException {
    long examId = RoutingHelpers.getUnsignedLongUrlParameter(request, UrlParameter.EXAM_ID);

    String reportType = request.queryParams(QUERY_PARAM_REPORT);
    if (reportType == null) {
      throw new InvalidParameterException("report query parameter is missing");
    }

    if (reportType.equals(ReportType.PASSED_PARTICIPATION_COMPARISON_REPORT.type())) {
      return new Gson().toJson(examReportService.getPassedParticipationComparisonReport(examId));
    } else if (reportType.equals(ReportType.EXERCISE_AVERAGE_MAX_POINTS_COMPARISON_REPORT.type())) {
      return new Gson()
          .toJson(examReportService.getExerciseAverageMaxPointsComparisonReport(examId));
    } 
    throw new InvalidParameterException("report query parameter is unknown");
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
