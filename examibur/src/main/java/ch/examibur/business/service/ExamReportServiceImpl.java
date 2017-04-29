package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.business.util.grading.GradingUtil;
import ch.examibur.business.util.grading.strategy.BaseGradingStrategy;
import ch.examibur.domain.ExamParticipation;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.service.ExamReportService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.report.PassedParticipationComparisonReport;
import com.google.inject.Inject;
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

  /**
   * Constructor.
   * 
   * @param examParticipation
   *          the data access object to access exam participations
   * @param exerciseGradingDao
   *          the data access object to access exercise gradings
   * @param examDao
   *          the data access object to access exams
   */
  @Inject
  public ExamReportServiceImpl(ExamParticipationDao examParticipation,
      ExerciseGradingDao exerciseGradingDao, ExamDao examDao) {
    this.examParticipationDao = examParticipation;
    this.exerciseGradingDao = exerciseGradingDao;
    this.examDao = examDao;
  }

  @Override
  public PassedParticipationComparisonReport getPassedParticipationComparisonReport(long examId)
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
      return new PassedParticipationComparisonReport(passedParticipations,
          examParticipations.size());
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "Exam, exam participations or exercise gradings with exam id " + examId
              + " does/do not exist",
          ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
