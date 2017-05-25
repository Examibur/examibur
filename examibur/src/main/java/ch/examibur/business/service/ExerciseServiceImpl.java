package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.Exercise;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.service.ExerciseService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import com.google.inject.Inject;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseServiceImpl implements ExerciseService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseServiceImpl.class);

  private final ExerciseDao exerciseDao;

  @Inject
  public ExerciseServiceImpl(ExerciseDao exerciseDao) {
    this.exerciseDao = exerciseDao;
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    return exerciseDao.getExercises(examId);
  }

  @Override
  public Exercise getExercise(long exerciseId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(exerciseId, LOGGER);
    try {
      return exerciseDao.getExercise(exerciseId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(ex.getMessage(), ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
