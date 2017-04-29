package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;

import java.util.List;

public interface ExamDao {

  public List<Exam> getExamsForAuthor(long authorId);

  public Exam getExam(long examId);
  
  /**
   * @param examId
   *          the id of the exam. If the exam is not
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   */
  public double getMaxPoints(long examId);

}
