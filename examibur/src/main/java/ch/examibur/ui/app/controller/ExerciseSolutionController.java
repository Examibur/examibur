package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.filter.Filters.MODEL;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.exercisegrading.ExerciseGradingService;
import ch.examibur.business.exercisesolution.ExerciseSolutionService;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.ui.app.util.Renderer;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ExerciseSolutionController implements Controller {

  public static final String PARAM_SOLUTION_ID = ":solutionId";
  private final Renderer engine;
  private final ExerciseSolutionService exerciseSolutionService;
  private final ExerciseGradingService exerciseGradingService;

  /**
   * Constructor.
   * 
   * @param engine
   *          the render engine to render the templates with
   * @param exerciseSolutionService
   *          the service to access exerciseSolutions
   * @param exerciseGradingService
   *          the service to access exerciseGradings
   */
  @Inject
  public ExerciseSolutionController(Renderer engine,
      ExerciseSolutionService exerciseSolutionService,
      ExerciseGradingService exerciseGradingService) {
    this.engine = engine;
    this.exerciseSolutionService = exerciseSolutionService;
    this.exerciseGradingService = exerciseGradingService;
  }

  /**
   * Returns a specific exercise solution.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   * @throws NotFoundException
   *           if the exerciseSolution is not found
   * @throws AuthorizationException
   *           if the user is not authorized to display this {@link ExerciseSolution}
   * @throws IOException
   *           if an exception during the communication occurs
   * 
   */
  public String displayExerciseSolution(Request request, Response response)
      throws NotFoundException, AuthorizationException, IOException {
    long exerciseSolutionId = Long.parseLong(request.params(PARAM_SOLUTION_ID));
    Map<String, Object> model = request.attribute(MODEL);
    model.put("exerciseSolution", exerciseSolutionService.getExerciseSolution(exerciseSolutionId));
    model.put("grading", exerciseGradingService.getGradingForExerciseSolution(exerciseSolutionId));
    model.put("review", exerciseGradingService.getReviewForExerciseSolution(exerciseSolutionId));
    return engine.render(model, "exerciseSolutionView.ftl");
  }

  /**
   * Returns a list of exercise solutions.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   * @return the rendered page content
   */
  public String listExerciseSolutions(Request request, Response response) {
    Map<String, Object> model = request.attribute(MODEL);
    return engine.render(model, "404.ftl");
  }

}
