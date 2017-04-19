package ch.examibur.business.exercise;

import ch.examibur.business.AuthorizationException;
import ch.examibur.business.NotFoundException;
import ch.examibur.integration.exercise.ExerciseDao;
import com.google.inject.Inject;
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
  public double getMaxPoints(long examId) throws NotFoundException, AuthorizationException {
    if (examId < 0) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
          "exam id is negative");
      LOGGER.error(illegalArgumentException.getMessage(), illegalArgumentException);
      throw illegalArgumentException;
    }

    try {
      return exerciseDao.getMaxPoints(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "Exam with id " + examId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
