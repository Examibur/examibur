package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExerciseSolutionOverview;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseSolutionServiceImpl implements ExerciseSolutionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionServiceImpl.class);

  private final ExerciseSolutionDao exerciseSolutionDao;
  private final ExerciseGradingDao exerciseGradingDao;

  @Inject
  public ExerciseSolutionServiceImpl(ExerciseSolutionDao exerciseSolutionDao,
      ExerciseGradingDao exerciseGradingDao) {
    this.exerciseSolutionDao = exerciseSolutionDao;
    this.exerciseGradingDao = exerciseGradingDao;
  }

  @Override
  public ExerciseSolution getExerciseSolution(long exerciseSolutionId) throws ExamiburException {
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
  public List<ExerciseSolutionOverview> getExerciseSolutionsForExamParticipation(
      long examParticipationId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examParticipationId, LOGGER);

    LOGGER.info("Get ExerciseSolutions for ExamParticipation with id {}", examParticipationId);
    List<ExerciseSolution> exerciseSolutions;
    try {
      exerciseSolutions = exerciseSolutionDao
          .getExerciseSolutionsForExamParticipation(examParticipationId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExamParticipation with id " + examParticipationId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }

    List<ExerciseSolutionOverview> overviewList = new ArrayList<>();
    for (ExerciseSolution exerciseSolution : exerciseSolutions) {
      Optional<Double> points = exerciseGradingDao
          .getPointsOfExerciseSolution(exerciseSolution.getId());
      overviewList.add(new ExerciseSolutionOverview(exerciseSolution, points));
    }
    return overviewList;
  }

  @Override
  public ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId)
      throws ExamiburException {
    String logInfo = "Get ExerciseSolution of the same Exercise from the next Participation "
        + "(current ExerciseSolutionId " + currentExerciseSolutionId + ")";

    return getNextExerciseSolution(currentExerciseSolutionId, logInfo,
        exerciseSolutionDao::getExerciseSolutionFromNextParticipation);
  }

  @Override
  public ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId)
      throws ExamiburException {
    String logInfo = "Get ExerciseSolution of the next Exercise from the same Participation "
        + "(current ExerciseSolutionId " + currentExerciseSolutionId + ")";

    return getNextExerciseSolution(currentExerciseSolutionId, logInfo,
        exerciseSolutionDao::getNextExerciseSolutionFromParticipation);
  }

  private ExerciseSolution getNextExerciseSolution(long currentExerciseSolutionId, String logInfo,
      Function<Long, ExerciseSolution> queryFunction) throws ExamiburException {
    ValidationHelper.checkForNegativeId(currentExerciseSolutionId, LOGGER);
    LOGGER.info(logInfo);
    try {
      return queryFunction.apply(currentExerciseSolutionId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + currentExerciseSolutionId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }
}
