package ch.examibur.ui.app.routing;

import ch.examibur.integration.SingleResultNotFoundException;
import ch.examibur.ui.app.controller.DashboardController;
import ch.examibur.ui.app.controller.ExamController;
import ch.examibur.ui.app.controller.ExamParticipationController;
import ch.examibur.ui.app.controller.ExceptionController;
import ch.examibur.ui.app.controller.ExerciseController;
import ch.examibur.ui.app.controller.ExerciseGradingController;
import ch.examibur.ui.app.controller.ExerciseSolutionController;
import ch.examibur.ui.app.filter.Filters;
import com.google.inject.Inject;
import spark.Filter;
import spark.Spark;

public final class Router {

  @Inject
  DashboardController dashboardController;

  @Inject
  ExamController examController;

  @Inject
  ExerciseController exerciseController;

  @Inject
  ExamParticipationController participationController;

  @Inject
  ExerciseSolutionController exerciseSolutionController;

  @Inject
  ExerciseGradingController exerciseGradingController;

  @Inject
  ExceptionController exceptionController;

  /**
   * defines the HTTP routes for the application.
   */
  public void route() {
    Spark.staticFiles.location("/public");

    Spark.before("*", Filters::addTrailingSlashes);
    Spark.before("*", Filters::handleAuthentication);
    Spark.before("*", Filters::addBaseModel);

    beforeAll(Routes.ROOT, dashboardController::addBreadCrumb);
    get(Routes.ROOT, dashboardController::displayDashboard);

    beforeAll(Routes.EXAMS, examController::addBreadCrumb);
    get(Routes.EXAMS, examController::listExams);

    beforeAll(Routes.EXAM, examController::addSpecificBreadCrumb);
    get(Routes.EXAM, examController::displayExam);
    post(Routes.EXAM, examController::updateExam);

    beforeAll(Routes.EXERCISES, exerciseController::addBreadCrumb);
    get(Routes.EXERCISES, exerciseController::listExercises);

    beforeAll(Routes.EXERCISE, exerciseController::addSpecificBreadCrumb);
    get(Routes.EXERCISE, exerciseController::displayExercise);

    beforeAll(Routes.PARTICIPANTS, participationController::addBreadCrumb);
    get(Routes.PARTICIPANTS, participationController::listExamParticipations);

    beforeAll(Routes.PARTICIPANT, participationController::addSpecificBreadCrumb);
    get(Routes.PARTICIPANT, participationController::displayExamParticipation);

    beforeAll(Routes.SOLUTIONS, exerciseSolutionController::addBreadCrumb);
    get(Routes.SOLUTIONS, exerciseSolutionController::listExerciseSolutions);

    beforeAll(Routes.SOLUTION, exerciseSolutionController::addSpecificBreadCrumb);
    get(Routes.SOLUTION, exerciseSolutionController::displayExerciseSolution);
    post(Routes.SOLUTION, exerciseGradingController::addExerciseGrading);

    Spark.get("*", exceptionController::handleNotFound);
    Spark.exception(SingleResultNotFoundException.class,
        exceptionController::handleNotFoundException);
    Spark.exception(Exception.class, exceptionController::handleException);
    Spark.after("*", Filters::addGzipHeader);
  }

  private static void get(Routes uri, spark.Route sparkRoute) {
    Spark.get(uri.url(), sparkRoute);
  }

  private static void post(Routes uri, spark.Route sparkRoute) {
    Spark.post(uri.url(), sparkRoute);
  }

  private static void beforeAll(Routes uri, Filter filter) {
    Spark.before(uri.url() + '*', filter);
  }

}
