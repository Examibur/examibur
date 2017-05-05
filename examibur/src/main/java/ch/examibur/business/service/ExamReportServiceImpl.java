package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import ch.examibur.integration.report.ExamReportDao;
import ch.examibur.service.ExamReportService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import com.google.inject.Inject;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamReportServiceImpl implements ExamReportService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamReportServiceImpl.class);

  private final ExamReportDao examReportDao;

  /**
   * Constructor.
   * 
   * @param examReportDao
   *          the data access object to generate exam reports
   */
  @Inject
  public ExamReportServiceImpl(ExamReportDao examReportDao) {
    this.examReportDao = examReportDao;
  }

  @Override
  public PassedParticipationComparison getPassedParticipationComparisonReport(long examId)
      throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    try {
      return examReportDao.getPassedParticipationComparisonReport(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "PassedParticipationComparisonReport could not be generated with examId " + examId, ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    try {
      return examReportDao.getExerciseAverageMaxPointsComparisonReport(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseAverageMaxPointsComparisonReport could not be generated with examId " + examId,
          ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
