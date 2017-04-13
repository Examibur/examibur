package ch.examibur.ui.app.router;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

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

  public void route() {
    before("*", Filters::addTrailingSlashes);
    before("*", Filters::handleAuthentication);
    before("*", Filters::addBaseModel);

    get("/", dashboardController::displayDashboard);

    get("/exams", examController::listExams);
    path("/exams/:examId", () -> {
      get("/", examController::displayExam);
      post("/", examController::updateExam);

      path("/exercises", () -> {
        get("/", exerciseController::listExercises);
        get("/:exerciseId", exerciseController::displayExercise);
      });

      path("/participants", () -> {

        get("/", participationController::listExamParticipations);
        path("/:participantId", () -> {

          get("/", participationController::displayExamParticipation);
          path("/solutions", () -> {
            get("/", exerciseSolutionController::listExerciseSolutions);
            get("/:solutionId", exerciseSolutionController::displayExerciseSolution);
            post("/:solutionId", exerciseGradingController::addExerciseGrading);
          });

        });
      });

    });

    get("*", exceptionController::handleNotFound);

    exception(Exception.class, exceptionController::handleException);

    after("*", Filters::addGzipHeader);

  }

}
