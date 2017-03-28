package ch.examibur.business.exam;

import java.util.List;

import ch.examibur.domain.Exam;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;

public final class ExamServiceImpl implements ExamService {

  private final ExamDao examDao;

  public ExamServiceImpl() {
    examDao = new ExamDaoImpl();
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    return examDao.getExamsForAuthor(authorId);
  }

}
