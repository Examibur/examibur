package ch.examibur.business.exercisegrading;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.User;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import com.google.inject.Inject;
import javax.persistence.NoResultException;
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
    LOGGER.info("Get Grading for ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.CORRECTION);
  }

  @Override
  public ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId) {
    LOGGER.info("Get Review for ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.REVIEW);
  }

  private ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state) {
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);
    return exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, state);
  }

  @Override
  public void addGrading(long exerciseSolutionId, String comment, String reasoning, double points)
      throws NotFoundException, AuthorizationException {
    LOGGER.info("Add Grading for ExerciseSolution {}", exerciseSolutionId);
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);

    // TODO load current user
    User gradingAuthor = new User();
    gradingAuthor.setId(1L);
    gradingAuthor.setFirstName("Maximilian");
    gradingAuthor.setLastName("Mueller");

    try {
      exerciseGradingDao.addGrading(exerciseSolutionId, comment, reasoning, points, gradingAuthor);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + exerciseSolutionId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
