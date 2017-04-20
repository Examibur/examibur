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

public class ExerciseController implements Controller {

  public static final String PARAM_EXERCISE_ID = ":exerciseId";
  private final Renderer engine;

  @Inject
  public ExerciseController(Renderer engine) {
    this.engine = engine;
  }

  /**
   * Returns a specific exercise.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String displayExercise(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "404.ftl");
  }

  /**
   * Returns a list of exercises.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String listExercises(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "404.ftl");
  }

  /**
   * Adds breadcurmb for `exercises/`.
   */
  @SuppressWarnings("unchecked")
  public void addBreadCrumb(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    List<BreadCrumbEntry> breadcrumb = (List<BreadCrumbEntry>) model.get(Filters.BREADCRUMB);
    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));
    breadcrumb.add(new BreadCrumbEntry("Aufgaben", "/exams/" + examId + "/exercises/"));
  }

  /**
   * Adds breadcurmb for `exercises/:exerciseId`.
   */
  @SuppressWarnings("unchecked")
  public void addSpecificBreadCrumb(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    List<BreadCrumbEntry> breadcrumb = (List<BreadCrumbEntry>) model.get(Filters.BREADCRUMB);

    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));
    long exerciseId = Long.parseLong(request.params(PARAM_EXERCISE_ID));
    breadcrumb.add(new BreadCrumbEntry("Aufgabe #" + exerciseId,
        "/exams/" + examId + "/exercises/" + exerciseId));
  }
}
