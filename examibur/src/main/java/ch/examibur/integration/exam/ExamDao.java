package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import java.util.List;
import javax.persistence.EntityManager;

public interface ExamDao {

  public List<Exam> getExamsForAuthor(long authorId);

  public List<Exam> getExamsForAuthor(long authorId, ExamState state);

  public Exam getExam(long examId);

  /**
   * @param examId
   *          the id of the exam
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   */
  public double getMaxPoints(long examId);

  /**
   * @see ch.examibur.integration.exam.ExamDao#getMaxPoints(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  public double getMaxPoints(long examId, EntityManager entityManager);

}
