package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exercise.ExerciseService;
import ch.examibur.ui.app.util.Renderer;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExamController implements Controller {

  public static final String PARAM_EXAM_ID = ":examId";

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
   */
  public String displayExam(Request request, Response response) {
    long examId = Long.parseLong(request.params(PARAM_EXAM_ID));

    Map<String, Object> model = request.attribute(MODEL);
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
    Map<String, Object> model = request.attribute(MODEL);
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

}
