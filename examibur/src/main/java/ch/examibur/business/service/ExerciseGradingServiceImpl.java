package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.User;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
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

  public ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get Grading vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.CORRECTION);
  }

  @Override
  public ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get Review vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.REVIEW);
  }

  private ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state)
      throws InvalidParameterException {
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);
    return exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, state);
  }

  @Override
  public void addGrading(long exerciseSolutionId, String comment, String reasoning, double points)
      throws ExamiburException {
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
