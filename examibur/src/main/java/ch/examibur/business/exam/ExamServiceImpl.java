package ch.examibur.business.exam;

import ch.examibur.domain.Exam;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;

import com.google.inject.Inject;

import java.util.List;

public final class ExamServiceImpl implements ExamService {

  private final ExamDao examDao;

  @Inject
  public ExamServiceImpl(ExamDaoImpl examDao) {
    this.examDao = examDao;
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    return examDao.getExamsForAuthor(authorId);
  }

  @Override
  public Exam getExam(long examId) {
    return examDao.getExam(examId);
  }

}
