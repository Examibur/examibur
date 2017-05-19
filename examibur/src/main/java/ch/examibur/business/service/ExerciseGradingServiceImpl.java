package ch.examibur.business.service;

import ch.examibur.business.util.AuthenticationUtil;
import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.User;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import ch.examibur.service.ExerciseGradingService;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ApprovalResult;
import com.google.inject.Inject;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingServiceImpl implements ExerciseGradingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseGradingServiceImpl.class);

  private final ExerciseGradingDao exerciseGradingDao;
  private final ExerciseSolutionDao exerciseSolutionDao;

  /**
   * Constructor for ExerciseGradingServiceImpl.
   */
  @Inject
  public ExerciseGradingServiceImpl(ExerciseGradingDao exerciseGradingDao,
      ExerciseSolutionDao exerciseSolutionDao) {
    this.exerciseGradingDao = exerciseGradingDao;
    this.exerciseSolutionDao = exerciseSolutionDao;

  }

  @Override

  public ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get Grading vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.CORRECTION);
  }

  @Override
  public ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId)
      throws ExamiburException {
    LOGGER.info("Get Review vor ExerciseSolution {}", exerciseSolutionId);
    return getGradingCreatedInState(exerciseSolutionId, ExamState.REVIEW);
  }

  private ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state)
      throws InvalidParameterException {
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);
    return exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, state);
  }

  @Override
  public void addGrading(long exerciseSolutionId, String comment, String reasoning, double points)
      throws ExamiburException {
    LOGGER.info("Add Grading for ExerciseSolution {}", exerciseSolutionId);
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);

    User gradingAuthor = AuthenticationUtil.getCurrentUser();
    if (gradingAuthor == null) {
      AuthorizationException authorizationException = new AuthorizationException(
          "No user logged in to create the grading");
      LOGGER.error(authorizationException.getMessage(), authorizationException);
      throw authorizationException;
    }

    try {
      exerciseGradingDao.addGrading(exerciseSolutionId, comment, reasoning, points, gradingAuthor);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "ExerciseSolution with id " + exerciseSolutionId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public void approveReview(long exerciseSolutionId, long exerciseGradingId, String approvalResult)
      throws ExamiburException {
    ValidationHelper.checkForNegativeId(exerciseSolutionId, LOGGER);
    ValidationHelper.checkForNegativeId(exerciseGradingId, LOGGER);

    ExerciseGrading reviewForSolution =
        exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, ExamState.REVIEW);
    ExerciseGrading gradingForSolution =
        exerciseGradingDao.getGradingCreatedInState(exerciseSolutionId, ExamState.CORRECTION);

    if (reviewForSolution == null || gradingForSolution == null) {
      throw new IllegalOperationException(
          "Review or grading missing for ExerciseSolution with id " + exerciseSolutionId);
    }
    if (reviewForSolution.getExerciseSolution().getId() != exerciseSolutionId) {
      throw new IllegalOperationException("Review " + exerciseGradingId
          + " does not belong to ExerciseSolution " + exerciseSolutionId);
    }
    ApprovalResult approval = ValidationHelper.getValidatedApprovalResult(approvalResult, LOGGER);

    LOGGER.info("Approve Review for solution {}: {}", exerciseSolutionId, approval);
    if (approval == ApprovalResult.ACCEPT) {
      exerciseGradingDao.setFinalGrading(reviewForSolution.getId(), true);
      exerciseGradingDao.setFinalGrading(gradingForSolution.getId(), false);
    } else if (approval == ApprovalResult.REJECT) {
      exerciseGradingDao.setFinalGrading(reviewForSolution.getId(), false);
      exerciseGradingDao.setFinalGrading(gradingForSolution.getId(), true);
    }
    exerciseSolutionDao.setIsDone(exerciseSolutionId, true);

  }

}
