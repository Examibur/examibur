package ch.examibur.business.exercisegrading;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingServiceImpl implements ExerciseGradingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseGradingServiceImpl.class);

  private final ExerciseGradingDao exerciseGradingDao;

  @Inject
  public ExerciseGradingServiceImpl(ExerciseGradingDao exerciseGradingDao) {
    this.exerciseGradingDao = exerciseGradingDao;
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
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);
    return exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, state);
  }

}
