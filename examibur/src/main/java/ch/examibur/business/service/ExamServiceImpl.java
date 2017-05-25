package ch.examibur.business.service;

import ch.examibur.business.util.ValidationHelper;
import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;
import ch.examibur.service.ExamService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.NotFoundException;
import com.google.inject.Inject;
import java.util.List;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExamServiceImpl implements ExamService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

  private final ExamDao examDao;

  @Inject
  public ExamServiceImpl(ExamDaoImpl examDao) {
    this.examDao = examDao;
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(authorId, LOGGER);
    return examDao.getExamsForAuthor(authorId);
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId, ExamState state) throws ExamiburException {
    ValidationHelper.checkForNegativeId(authorId, LOGGER);
    return examDao.getExamsForAuthor(authorId, state);
  }

  @Override
  public Exam getExam(long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);
    try {
      return examDao.getExam(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException =
          new NotFoundException("Exam with id " + examId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public double getMaxPoints(long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    try {
      return examDao.getMaxPoints(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException =
          new NotFoundException("Exam with id " + examId + " does not exist", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

  @Override
  public void setNextState(long examId) throws ExamiburException {
    ValidationHelper.checkForNegativeId(examId, LOGGER);

    Exam exam = getExam(examId);
    ExamState nextState = getNextState(exam.getState());
    checkIsDone(examId);

    examDao.changeState(examId, nextState);
  }

  private ExamState getNextState(ExamState currentState) throws IllegalOperationException {
    ExamState nextState = ExamState.forName(currentState.getOrder() + 1);
    if (nextState == null) {
      IllegalOperationException illegalOperationException = new IllegalOperationException(
          "Last state reached. No more transitions possible for this Exam.");
      LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
      throw illegalOperationException;
    }
    return nextState;
  }

  private void checkIsDone(long examId) throws IllegalOperationException {
    if (!examDao.isFinished(examId)) {
      IllegalOperationException illegalOperationException =
          new IllegalOperationException("The Exam with id " + examId
              + " has unfinished ExerciseSolutions and therefore can not change its state.");
      LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
      throw illegalOperationException;
    }
  }

}
