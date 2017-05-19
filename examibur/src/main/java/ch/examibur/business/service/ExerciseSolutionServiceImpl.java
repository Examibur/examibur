package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import ch.examibur.integration.util.grading.GradingUtil;
import ch.examibur.integration.util.grading.strategy.BaseGradingStrategy;
import ch.examibur.service.ExerciseSolutionService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExerciseParticipantOverview;
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
  private final ExamDao examDao;

  @Inject
  public ExerciseSolutionServiceImpl(ExerciseSolutionDao exerciseSolutionDao,
      ExerciseGradingDao exerciseGradingDao, ExamDao examDao) {
    this.exerciseSolutionDao = exerciseSolutionDao;
    this.exerciseGradingDao = exerciseGradingDao;
    this.examDao = examDao;
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
  public ExerciseSolution getFirstExerciseSolutionFromParticipation(long participationId)
      throws ExamiburException {
    LOGGER.info("Get the first ExerciseSolution of a Participation (ParticipationId {})",
        participationId);

    return getNextExerciseSolution(participationId,
        exerciseSolutionDao::getFirstExerciseSolutionFromParticipation);
  }

  @Override
  public ExerciseSolution getFirstExerciseSolutionFromExercise(long exerciseId)
      throws ExamiburException {
    LOGGER.info("Get the first ExerciseSolution of an Exercise (ExerciseId {})", exerciseId);

    return getNextExerciseSolution(exerciseId,
        exerciseSolutionDao::getFirstExerciseSolutionFromExercise);
  }

  @Override
  public ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get ExerciseSolution of the same Exercise from the next Participation "
        + "(current ExerciseSolutionId {})", currentExerciseSolutionId);

    return getNextExerciseSolution(currentExerciseSolutionId,
        exerciseSolutionDao::getExerciseSolutionFromNextParticipation);
  }

  @Override
  public ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get ExerciseSolution of the next Exercise from the same Participation "
        + "(current ExerciseSolutionId {})", currentExerciseSolutionId);

    return getNextExerciseSolution(currentExerciseSolutionId,
        exerciseSolutionDao::getNextExerciseSolutionFromParticipation);
  }

  private ExerciseSolution getNextExerciseSolution(long resourceId,
      Function<Long, ExerciseSolution> queryFunction) throws ExamiburException {
    ValidationHelper.checkForNegativeId(resourceId, LOGGER);
    try {
      return queryFunction.apply(resourceId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + resourceId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public List<ExerciseParticipantOverview> getExerciseParticipantsOverview(long exerciseId)
      throws ExamiburException {
    ValidationHelper.checkForNegativeId(exerciseId, LOGGER);

    List<ExerciseSolution> exerciseSolutions = exerciseSolutionDao
        .getExerciseSolutionsForExercise(exerciseId);
    List<ExerciseParticipantOverview> exerciseParticipantsOverview = new ArrayList<>();

    for (ExerciseSolution exerciseSolution : exerciseSolutions) {
      ExerciseParticipantOverview exerciseParticipantOverview = new ExerciseParticipantOverview();

      exerciseParticipantOverview.setId(exerciseSolution.getParticipation().getId());
      exerciseParticipantOverview.setPseudonym(exerciseSolution.getParticipation().getPseudonym());
      exerciseParticipantOverview.setDone(exerciseSolution.isDone());

      long examId = exerciseSolution.getExercise().getExam().getId();
      long examParticipationId = exerciseSolution.getParticipation().getId();
      boolean areAllExercisesAreGraded = exerciseGradingDao.checkIfAllExercisesAreGraded(examId,
          examParticipationId);

      double totalPoints = exerciseGradingDao.getTotalPointsOfExamGradings(examParticipationId);
      exerciseParticipantOverview.setTotalPoints(totalPoints);

      if (areAllExercisesAreGraded) {
        double maxPoints = examDao.getMaxPoints(examId);

        double grading = GradingUtil.calculateGrading(new BaseGradingStrategy(), totalPoints,
            maxPoints);
        exerciseParticipantOverview.setGrading(Optional.of(grading));
      } else {
        exerciseParticipantOverview.setGrading(Optional.empty());
      }

      exerciseParticipantsOverview.add(exerciseParticipantOverview);
    }
    return exerciseParticipantsOverview;
  }
}
