package ch.examibur.ui.app.module;

import ch.examibur.business.exam.ExamService;
import ch.examibur.business.exam.ExamServiceImpl;
import ch.examibur.business.exercise.ExerciseService;
import ch.examibur.business.exercise.ExerciseServiceImpl;
import ch.examibur.business.exercisegrading.ExerciseGradingService;
import ch.examibur.business.exercisegrading.ExerciseGradingServiceImpl;
import ch.examibur.business.exercisesolution.ExerciseSolutionService;
import ch.examibur.business.exercisesolution.ExerciseSolutionServiceImpl;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ExamService.class).to(ExamServiceImpl.class);
    bind(ExerciseService.class).to(ExerciseServiceImpl.class);
    bind(ExerciseGradingService.class).to(ExerciseGradingServiceImpl.class);
    bind(ExerciseSolutionService.class).to(ExerciseSolutionServiceImpl.class);

  }

}