package ch.examibur.business.exam;

import ch.examibur.domain.Exam;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExamServiceImpl implements ExamService {

  private final ExamDao examDao;
  final Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

  public ExamServiceImpl() {
    examDao = new ExamDaoImpl();
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    logger.info("getExamsForAuthor");
    return examDao.getExamsForAuthor(authorId);
  }

}
