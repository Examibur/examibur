package ch.examibur.business.exam;

import java.util.List;

import ch.examibur.domain.Exam;

public interface ExamService {

  public List<Exam> getExamsForAuthor(long authorId);

}
