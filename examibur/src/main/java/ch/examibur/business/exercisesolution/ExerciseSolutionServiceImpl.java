package ch.examibur.business.exercisesolution;

import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import com.google.inject.Inject;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseSolutionServiceImpl implements ExerciseSolutionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionServiceImpl.class);

  private final ExerciseSolutionDao exerciseSolutionDao;

  @Inject
  public ExerciseSolutionServiceImpl(ExerciseSolutionDao exerciseSolutionDao) {
    this.exerciseSolutionDao = exerciseSolutionDao;
  }

  @Override
  public ExerciseSolution getExerciseSolution(long exerciseSolutionId) throws NotFoundException {
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);

    LOGGER.info("Get ExerciseSolution with id {}", exerciseSolutionId);
    try {
      return exerciseSolutionDao.getExerciseSolution(exerciseSolutionId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + exerciseSolutionId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public long getExerciseSolutionIdFromNextParticipation(long currentExerciseSolutionId)
      throws NotFoundException {
    ValidationHelper.checkForNegativeId(currentExerciseSolutionId, LOGGER);

    LOGGER.info("Get ExerciseSolutionId from next Participation (current id {})",
        currentExerciseSolutionId);
    try {
      return exerciseSolutionDao
          .getExerciseSolutionIdFromNextParticipation(currentExerciseSolutionId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + currentExerciseSolutionId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
