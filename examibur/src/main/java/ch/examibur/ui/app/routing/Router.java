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
import ch.examibur.ui.app.url.Routes;
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

    beforeAll(Routes.ROOT, dashboardController::addBreadCrumb);
    get(Routes.ROOT, dashboardController::displayDashboard);

    beforeAll(Routes.LOGIN, authenticationController::addBreadCrumb);
    get(Routes.LOGIN, authenticationController::displayLoginForm);
    post(Routes.LOGIN, authenticationController::performLogin);
    get(Routes.LOGOUT, authenticationController::logout);

    get(Routes.QUERY_NEXT_SOLUTION, browseSolutionController::getNextExerciseSolution);
    get(Routes.QUERY_FIRST_SOLUTION_BY_EXERCISE,
        browseSolutionController::getFirstExerciseSolutionByExercise);
    get(Routes.QUERY_FIRST_SOLUTION_BY_EXERCISES,
        browseSolutionController::getFirstExerciseSolutionByExercises);
    get(Routes.QUERY_FIRST_SOLUTION_BY_PARTICIPATION,
        browseSolutionController::getFirstExerciseSolutionByParticipation);
    get(Routes.QUERY_FIRST_SOLUTION_BY_PARTICIPATIONS,
        browseSolutionController::getFirstExerciseSolutionByParticipations);

    beforeAll(Routes.EXAMS, examController::addBreadCrumb);
    get(Routes.EXAMS, examController::listExams);

    beforeAll(Routes.EXAM, examController::addSpecificBreadCrumb);
    get(Routes.EXAM, examController::displayExam);
    post(Routes.EXAM, examController::setNextExamState);

    beforeAll(Routes.EXERCISES, exerciseController::addBreadCrumb);
    get(Routes.EXERCISES, exerciseController::listExercises);

    beforeAll(Routes.EXERCISE, exerciseController::addSpecificBreadCrumb);
    get(Routes.EXERCISE, exerciseController::displayExercise);
    
    beforeAll(Routes.EXERCISE_PARTICIPANTS, exerciseParticipationController::addBreadCrumb);
    get(Routes.EXERCISE_PARTICIPANTS, exerciseParticipationController::listExerciseParticipations);

    beforeAll(Routes.PARTICIPANTS, participationController::addBreadCrumb);
    get(Routes.PARTICIPANTS, participationController::listExamParticipations);

    beforeAll(Routes.REPORTS, examReportController::addBreadCrumb);
    get(Routes.REPORTS, examReportController::displayReports);
    get(Routes.REPORTS_JSON, examReportController::getReportAsJson);

    beforeAll(Routes.PARTICIPANT, participationController::addSpecificBreadCrumb);
    get(Routes.PARTICIPANT, participationController::displayExamParticipation);

    beforeAll(Routes.SOLUTIONS, exerciseSolutionController::addBreadCrumb);
    get(Routes.SOLUTIONS, exerciseSolutionController::listExerciseSolutions);

    beforeAll(Routes.SOLUTION, exerciseSolutionController::addSpecificBreadCrumb);
    get(Routes.SOLUTION, exerciseSolutionController::displayExerciseSolution);

    post(Routes.GRADINGS, exerciseGradingController::addExerciseGrading);
    post(Routes.GRADING, exerciseGradingController::processApproval);

    Spark.get("*", exceptionController::handleNotFound);
    Spark.exception(NotFoundException.class, exceptionController::handleNotFoundException);
    Spark.exception(NumberFormatException.class, exceptionController::handleNotFoundException);
    Spark.exception(AuthorizationException.class,
        exceptionController::handleAuthorizationException);
    Spark.exception(Exception.class, exceptionController::handleException);
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
