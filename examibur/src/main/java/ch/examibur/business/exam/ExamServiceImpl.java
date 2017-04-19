package ch.examibur.business.exam;

import ch.examibur.business.NotFoundException;
import ch.examibur.domain.Exam;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;
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
  public List<Exam> getExamsForAuthor(long authorId) {
    if (authorId < 0) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
          "author id is negative");
      LOGGER.error(illegalArgumentException.getMessage(), illegalArgumentException);
      throw illegalArgumentException;
    }
    return examDao.getExamsForAuthor(authorId);
  }

  @Override
  public Exam getExam(long examId) throws NotFoundException {
    if (examId < 0) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
          "exam id is negative");
      LOGGER.error(illegalArgumentException.getMessage(), illegalArgumentException);
      throw illegalArgumentException;
    }
    try {
      return examDao.getExam(examId);
    } catch (NoResultException ex) {
      NotFoundException notFoundException = new NotFoundException(
          "Exam with id " + examId + " not found", ex);
      LOGGER.error(notFoundException.getMessage(), notFoundException);
      throw notFoundException;
    }
  }

}
