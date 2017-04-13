package ch.examibur.business.exercisegrading;

import ch.examibur.business.exercisesolution.ExerciseSolutionServiceImpl;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingServiceImpl implements ExerciseGradingService {

  private final ExerciseGradingDao exerciseGradingDao;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionServiceImpl.class);
  
  public ExerciseGradingServiceImpl() {
    exerciseGradingDao = new ExerciseGradingDaoImpl();
  }
  
  @Override
  public ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId) {
    LOGGER.info("Get Grading vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.CORRECTION);

  }

  @Override
  public ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId) {
    LOGGER.info("Get Review vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.REVIEW);
  }
  
  private ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state) {
    return exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, state);
  }

}
