package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import java.util.List;

public interface ExamDao {

  List<Exam> getExamsForAuthor(long authorId);

}
