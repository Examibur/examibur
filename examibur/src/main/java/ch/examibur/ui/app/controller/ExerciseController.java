package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exercise.ExerciseService;
import ch.examibur.ui.app.util.Renderer;

import com.google.inject.Inject;

import java.util.Map;

import spark.Request;
import spark.Response;

public class ExerciseController implements Controller {

  public static final String PARAM_EXERCISE_ID = ":exerciseId";
  
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
  public ExerciseController(Renderer engine, ExamService examService, 
      ExerciseService exerciseService) {
    this.engine = engine;
    this.examService = examService;
    this.exerciseService = exerciseService;
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
    long examId = Long.parseLong(request.params(ExamController.PARAM_EXAM_ID));
    
    Map<String, Object> model = request.attribute(MODEL);
    model.put("exam", examService.getExam(examId));
    model.put("exercises", exerciseService.getExercises(examId));
    return engine.render(model, "examExercisesTab.ftl");
  }

}
