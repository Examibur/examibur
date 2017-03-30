package ch.examibur.integration.exam;

import java.util.List;

import ch.examibur.domain.Exam;

public interface ExamDao {

  List<Exam> getExamsForAuthor(long authorId);

}
