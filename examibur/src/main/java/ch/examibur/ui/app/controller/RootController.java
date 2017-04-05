package ch.examibur.ui.app.controller;

import static spark.Spark.after;
import static spark.Spark.before;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exam.ExamServiceImpl;
import ch.examibur.business.exercise.ExerciseService;
import ch.examibur.business.exercise.ExerciseServiceImpl;
import ch.examibur.ui.app.filter.Filters;

public class RootController extends Controller {

  public RootController() {
    super(null, "");
  }

  @Override
  public void route() {
    ExamService examService = new ExamServiceImpl();
    ExerciseService exerciseService = new ExerciseServiceImpl();

    DashboardController dashboardController = new DashboardController(this, examService,
        exerciseService);
    ExceptionController exceptionController = new ExceptionController(this);

    before("*", Filters::addTrailingSlashes);
    before("*", Filters::handleAuthentication);

    dashboardController.route();
    exceptionController.route();

    after("*", Filters::addGzipHeader);
  }

}
