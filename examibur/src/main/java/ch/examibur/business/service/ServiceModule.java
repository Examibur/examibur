package ch.examibur.business.service;

import ch.examibur.service.AuthenticationService;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.ExamService;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.ExerciseService;
import ch.examibur.service.ExerciseSolutionService;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ExamService.class).to(ExamServiceImpl.class);
    bind(ExerciseService.class).to(ExerciseServiceImpl.class);
    bind(ExerciseGradingService.class).to(ExerciseGradingServiceImpl.class);
    bind(ExerciseSolutionService.class).to(ExerciseSolutionServiceImpl.class);
    bind(ExamParticipationService.class).to(ExamParticipationServiceImpl.class);
    bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
  }

}