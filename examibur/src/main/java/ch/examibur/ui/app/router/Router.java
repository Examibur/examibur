package ch.examibur.ui.app.router;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

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
    staticFiles.location("/public");

    before("*", Filters::addTrailingSlashes);
    before("*", Filters::handleAuthentication);
    before("*", Filters::addBaseModel);

    get("/", dashboardController::displayDashboard);
    before("/*", dashboardController::addBreadCrumb);

    get("/exams", examController::listExams);
    before("/exams/*", examController::addBreadCrumb);
    path("/exams/:examId", () -> {
      before("/*", examController::addSpecificBreadCrumb);
      get("/", examController::displayExam);
      post("/", examController::updateExam);

      path("/exercises", () -> {
        before("/*", exerciseController::addBreadCrumb);
        get("/", exerciseController::listExercises);

        before("/:exerciseId/*", exerciseController::addSpecificBreadCrumb);
        get("/:exerciseId", exerciseController::displayExercise);
      });

      path("/participants", () -> {
        before("/*", participationController::addBreadCrumb);
        get("/", participationController::listExamParticipations);

        path("/:participantId", () -> {
          before("/*", participationController::addSpecificBreadCrumb);
          get("/", participationController::displayExamParticipation);

          path("/solutions", () -> {
            before("/*", exerciseSolutionController::addBreadCrumb);
            get("/", exerciseSolutionController::listExerciseSolutions);
            before("/:solutionId/*", exerciseSolutionController::addSpecificBreadCrumb);
            get("/:solutionId/", exerciseSolutionController::displayExerciseSolution);
            post("/:solutionId/", exerciseGradingController::addExerciseGrading);
          });
        });
      });
    });

    get("*", exceptionController::handleNotFound);
    exception(SingleResultNotFoundException.class, exceptionController::handleNotFoundException);
    exception(Exception.class, exceptionController::handleException);

    after("*", Filters::addGzipHeader);

  }

}
