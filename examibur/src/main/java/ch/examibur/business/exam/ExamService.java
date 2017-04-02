package ch.examibur.business.exam;

import ch.examibur.domain.Exam;
import java.util.List;

public interface ExamService {

  public List<Exam> getExamsForAuthor(long authorId);

}
