package ch.examibur.business.exam;

import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;

public final class ExamServiceImpl implements ExamService {

  private final ExamDao examDao;

  public ExamServiceImpl() {
    examDao = new ExamDaoImpl();
  }

  @Override
  public void loadExams() {
    examDao.loadAllExams();
  }

}
