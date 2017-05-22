package ch.examibur.ui.app.routing;

import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.ui.app.controller.AuthenticationController;
import ch.examibur.ui.app.controller.BrowseSolutionController;
import ch.examibur.ui.app.controller.DashboardController;
import ch.examibur.ui.app.controller.ExamController;
import ch.examibur.ui.app.controller.ExamParticipationController;
import ch.examibur.ui.app.controller.ExamReportController;
import ch.examibur.ui.app.controller.ExceptionController;
import ch.examibur.ui.app.controller.ExerciseController;
import ch.examibur.ui.app.controller.ExerciseGradingController;
import ch.examibur.ui.app.controller.ExerciseParticipationController;
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
  ExamReportController examReportController;

  @Inject
  ExerciseSolutionController exerciseSolutionController;

  @Inject
  ExerciseGradingController exerciseGradingController;
  
  @Inject
  ExerciseParticipationController exerciseParticipationController;

  @Inject
  ExceptionController exceptionController;

  @Inject
  AuthenticationController authenticationController;

  @Inject
  BrowseSolutionController browseSolutionController;

  @Inject
  Filters filters;

  /**
   * defines the HTTP routes for the application.
   */
  public void route() {
    Spark.staticFiles.location("/public");

    Spark.before("*", filters::addTrailingSlashes);
    Spark.before("*", filters::utf8Encoding);
    Spark.before("*", filters::handleAuthentication);
    Spark.before("*", filters::addBaseModel);

    beforeAll(Route.ROOT, dashboardController::addBreadCrumb);
    get(Route.ROOT, dashboardController::displayDashboard);

    beforeAll(Route.LOGIN, authenticationController::addBreadCrumb);
    get(Route.LOGIN, authenticationController::displayLoginForm);
    post(Route.LOGIN, authenticationController::performLogin);
    get(Route.LOGOUT, authenticationController::logout);

    get(Route.QUERY_NEXT_SOLUTION, browseSolutionController::getNextExerciseSolution);
    get(Route.QUERY_FIRST_SOLUTION_BY_EXERCISE,
        browseSolutionController::getFirstExerciseSolutionByExercise);
    get(Route.QUERY_FIRST_SOLUTION_BY_EXERCISES,
        browseSolutionController::getFirstExerciseSolutionByExercises);
    get(Route.QUERY_FIRST_SOLUTION_BY_PARTICIPATION,
        browseSolutionController::getFirstExerciseSolutionByParticipation);
    get(Route.QUERY_FIRST_SOLUTION_BY_PARTICIPATIONS,
        browseSolutionController::getFirstExerciseSolutionByParticipations);

    beforeAll(Route.EXAMS, examController::addBreadCrumb);
    get(Route.EXAMS, examController::listExams);

    beforeAll(Route.EXAM, examController::addSpecificBreadCrumb);
    get(Route.EXAM, examController::displayExam);
    post(Route.EXAM, examController::updateExam);

    beforeAll(Route.EXERCISES, exerciseController::addBreadCrumb);
    get(Route.EXERCISES, exerciseController::listExercises);

    beforeAll(Route.EXERCISE, exerciseController::addSpecificBreadCrumb);
    get(Route.EXERCISE, exerciseController::displayExercise);
    
    beforeAll(Route.EXERCISE_PARTICIPANTS, exerciseParticipationController::addBreadCrumb);
    get(Route.EXERCISE_PARTICIPANTS, exerciseParticipationController::listExerciseParticipations);

    beforeAll(Route.PARTICIPANTS, participationController::addBreadCrumb);
    get(Route.PARTICIPANTS, participationController::listExamParticipations);

    beforeAll(Route.REPORTS, examReportController::addBreadCrumb);
    get(Route.REPORTS, examReportController::displayReports);
    get(Route.REPORTS_JSON, examReportController::getReportAsJson);

    beforeAll(Route.PARTICIPANT, participationController::addSpecificBreadCrumb);
    get(Route.PARTICIPANT, participationController::displayExamParticipation);

    beforeAll(Route.SOLUTIONS, exerciseSolutionController::addBreadCrumb);
    get(Route.SOLUTIONS, exerciseSolutionController::listExerciseSolutions);

    beforeAll(Route.SOLUTION, exerciseSolutionController::addSpecificBreadCrumb);
    get(Route.SOLUTION, exerciseSolutionController::displayExerciseSolution);

    post(Route.GRADINGS, exerciseGradingController::addExerciseGrading);
    post(Route.GRADING, exerciseGradingController::processApproval);

    Spark.get("*", exceptionController::handleNotFound);
    Spark.exception(NotFoundException.class, exceptionController::handleNotFoundException);
    Spark.exception(NumberFormatException.class, exceptionController::handleNotFoundException);
    Spark.exception(AuthorizationException.class,
        exceptionController::handleAuthorizationException);
    Spark.exception(Exception.class, exceptionController::handleException);
  }

  private static void get(Route uri, spark.Route sparkRoute) {
    Spark.get(uri.url(), sparkRoute);
  }

  private static void post(Route uri, spark.Route sparkRoute) {
    Spark.post(uri.url(), sparkRoute);
  }

  private static void beforeAll(Route uri, Filter filter) {
    Spark.before(uri.url() + '*', filter);
  }

}
