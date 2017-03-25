package ch.examibur.integration.exam;

public final class ExamDaoImpl implements ExamDao {

  @Override
  public void loadAllExams() {
    System.out.println("DB ACCESS: loadAllExams");
  }

}
