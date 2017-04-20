package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.ui.app.filter.Filters;
import ch.examibur.ui.app.util.BreadCrumbEntry;
import ch.examibur.ui.app.util.Renderer;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExamParticipationController implements Controller {

  public static final String PARAM_PARTICIPANT_ID = ":participantId";
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
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "examParticipationTabExerciseView.ftl");
  }

  /**
   * Adds breadcurmb for `exercises/`.
   */
  @SuppressWarnings("unchecked")
  public void addBreadCrumb(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    List<BreadCrumbEntry> breadcrumb = (List<BreadCrumbEntry>) model.get(Filters.BREADCRUMB);
    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));
    breadcrumb.add(new BreadCrumbEntry("Teilnehmer", "/exams/" + examId + "/participants/"));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  @SuppressWarnings("unchecked")
  public void addSpecificBreadCrumb(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    List<BreadCrumbEntry> breadcrumb = (List<BreadCrumbEntry>) model.get(Filters.BREADCRUMB);

    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));
    long participantId = Long.parseLong(request.params(PARAM_PARTICIPANT_ID));
    breadcrumb.add(new BreadCrumbEntry("Teilnehmer #" + participantId,
        "/exams/" + examId + "/participants/" + participantId));
  }

}
