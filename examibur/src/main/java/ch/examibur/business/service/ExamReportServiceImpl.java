package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.business.util.grading.GradingUtil;
import ch.examibur.business.util.grading.strategy.BaseGradingStrategy;
import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.Exercise;
import ch.examibur.domain.TextExercise;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.service.ExamReportService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.report.ExerciseAverageMaxPointsComparison;
import ch.examibur.service.model.report.PassedParticipationComparison;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamReportServiceImpl implements ExamReportService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamReportServiceImpl.class);
  private static final double MIN_GRADING = 3.75;

  private final ExamParticipationDao examParticipationDao;
  private final ExerciseGradingDao exerciseGradingDao;
  private final ExamDao examDao;
  private final ExerciseDao exerciseDao;

  /**
   * Constructor.
   * 
   * @param examParticipation
   *          the data access object to access exam participations
   * @param exerciseGradingDao
   *          the data access object to access exercise gradings
   * @param examDao
   *          the data access object to access exams
   * @param exerciseDao
   *          the data access object to access exercises
   */
  @Inject
  public ExamReportServiceImpl(ExamParticipationDao examParticipation,
      ExerciseGradingDao exerciseGradingDao, ExamDao examDao, ExerciseDao exerciseDao) {
    this.examParticipationDao = examParticipation;
    this.exerciseGradingDao = exerciseGradingDao;
    this.examDao = examDao;
    this.exerciseDao = exerciseDao;
  }

  @Override
  public PassedParticipationComparison getPassedParticipationComparisonReport(long examId)
      throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    try {
      double maxPoints = examDao.getMaxPoints(examId);
      int passedParticipations = 0;

      List<ExamParticipation> examParticipations = examParticipationDao
          .getExamParticipations(examId);
      for (ExamParticipation examParticipation : examParticipations) {
        double totalPoints = exerciseGradingDao
            .getTotalPointsOfExamGradings(examParticipation.getId());

        double grading = GradingUtil.calculateGrading(new BaseGradingStrategy(), totalPoints,
            maxPoints);

        if (grading >= MIN_GRADING) {
          passedParticipations++;
        }
      }
      return new PassedParticipationComparison(passedParticipations, examParticipations.size());
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "Exam, exam participations or exercise gradings with exam id " + examId
              + " does/do not exist",
          ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    List<ExerciseAverageMaxPointsComparison> exerciseAverageMaxPointsComparisonList = new ArrayList<>();

    try {
      for (Exercise exercise : exerciseDao.getExercises(examId)) {
        // TODO remove this ugly cast as soon as issue 99 is resolved
        String title = ((TextExercise) exercise).getTitle();
        double averagePoints = exerciseGradingDao.getAveragePointsOfExercise(exercise.getId());
        exerciseAverageMaxPointsComparisonList.add(
            new ExerciseAverageMaxPointsComparison(title, exercise.getMaxPoints(), averagePoints));
      }
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException("Exercises with exam id " + examId
          + " do not exist or exerciseGradingDao::getAveragePointsOfExercise failed", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
    return exerciseAverageMaxPointsComparisonList;
  }

}
