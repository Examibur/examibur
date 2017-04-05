package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import java.util.List;

public interface ExamDao {

  public List<Exam> getExamsForAuthor(long authorId);
  
  public Exam getExam(long examId);

}
