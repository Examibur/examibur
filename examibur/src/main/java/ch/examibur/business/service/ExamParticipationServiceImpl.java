package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExamParticipation;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.util.grading.GradingUtil;
import ch.examibur.integration.util.grading.strategy.BaseGradingStrategy;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExamParticipantOverview;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamParticipationServiceImpl implements ExamParticipationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamParticipationServiceImpl.class);

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
  public ExamParticipationServiceImpl(ExamParticipationDao examParticipation,
      ExerciseGradingDao exerciseGradingDao, ExamDao examDao) {
    this.examParticipationDao = examParticipation;
    this.exerciseGradingDao = exerciseGradingDao;
    this.examDao = examDao;
  }

  @Override
  public List<ExamParticipantOverview> getExamParticipantsOverview(long examId) {
    List<ExamParticipation> examParticipations = examParticipationDao.getExamParticipations(examId);

    List<ExamParticipantOverview> examParticipantsOverview = new ArrayList<>();
    for (ExamParticipation examParticipation : examParticipations) {
      ExamParticipantOverview examParticipantOverview = new ExamParticipantOverview();

      examParticipantOverview.setId(examParticipation.getId());
      examParticipantOverview.setPseudonym(examParticipation.getPseudonym());

      long examParticipationId = examParticipation.getId();
      boolean areAllExercisesAreGraded = exerciseGradingDao.checkIfAllExercisesAreGraded(examId,
          examParticipationId);

      double totalPoints = exerciseGradingDao.getTotalPointsOfExamGradings(examParticipationId);
      examParticipantOverview.setTotalPoints(totalPoints);

      if (areAllExercisesAreGraded) {
        double maxPoints = examDao.getMaxPoints(examId);

        double grading = GradingUtil.calculateGrading(new BaseGradingStrategy(), totalPoints,
            maxPoints);
        examParticipantOverview.setGrading(Optional.of(grading));
      } else {
        examParticipantOverview.setGrading(Optional.empty());
      }
      double progress = exerciseGradingDao.getProgressOfExamGradings(examId, examParticipationId);
      examParticipantOverview.setProgress(progress);

      examParticipantsOverview.add(examParticipantOverview);
    }

    return examParticipantsOverview;
  }

  @Override
  public ExamParticipation getExamParticipation(long examParticipationId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examParticipationId, LOGGER);
    try {
      return examParticipationDao.getExamParticipation(examParticipationId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExampParticipation with id " + examParticipationId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
