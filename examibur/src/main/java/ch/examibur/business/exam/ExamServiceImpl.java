package ch.examibur.business.exam;

import ch.examibur.domain.Exam;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExamServiceImpl implements ExamService {

  private final ExamDao examDao;
  private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

  public ExamServiceImpl() {
    examDao = new ExamDaoImpl();
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    LOGGER.info("getExamsForAuthor");
    return examDao.getExamsForAuthor(authorId);
  }

  @Override
  public Exam getExam(long examId) {
    return examDao.getExam(examId);
  }

}
