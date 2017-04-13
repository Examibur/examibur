package ch.examibur.business.exercisesolution;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseSolutionServiceImpl implements ExerciseSolutionService {

  private final ExerciseSolutionDao exerciseSolutionDao;

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionServiceImpl.class);

  public ExerciseSolutionServiceImpl() {
    exerciseSolutionDao = new ExerciseSolutionDaoImpl();
  }

  @Override
  public ExerciseSolution getExerciseSolution(long exerciseSolutionId) {
    LOGGER.info("Get ExerciseSolution with id {}", exerciseSolutionId);
    return exerciseSolutionDao.getExerciseSolution(exerciseSolutionId);
  }

}
