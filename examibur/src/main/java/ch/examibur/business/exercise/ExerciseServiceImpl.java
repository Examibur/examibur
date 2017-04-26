package ch.examibur.business.exercise;

import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.Exercise;
import ch.examibur.integration.exercise.ExerciseDao;
import com.google.inject.Inject;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseServiceImpl implements ExerciseService {

  private final ExerciseDao exerciseDao;

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseServiceImpl.class);

  @Inject
  public ExerciseServiceImpl(ExerciseDao exerciseDao) {
    this.exerciseDao = exerciseDao;
  }

  @Override
  public double getMaxPoints(long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    try {
      return exerciseDao.getMaxPoints(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "Exam with id " + examId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    return exerciseDao.getExercises(examId);
  }

}
