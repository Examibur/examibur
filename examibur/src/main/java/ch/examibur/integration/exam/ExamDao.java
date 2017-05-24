package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.NotFoundException;
import java.util.List;
import javax.persistence.EntityManager;

public interface ExamDao {

  List<Exam> getExamsForAuthor(long authorId);

  List<Exam> getExamsForAuthor(long authorId, ExamState state);

  Exam getExam(long examId);

  /**
   * @param examId
   *          the id of the exam
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   */
  double getMaxPoints(long examId);

  /**
   * @see ch.examibur.integration.exam.ExamDao#getMaxPoints(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  double getMaxPoints(long examId, EntityManager entityManager);

  /**
   * Changes the {@link ExamState} of an {@link Exam} and resets all belonging
   * {@link ExerciseSolution} to unfinished (isDone = false).
   *
   * @throws NotFoundException
   *           if the {@link Exam} with the given id does not exist.
   */
  void changeState(long examId, ExamState newState) throws NotFoundException;

  /**
   * Checks if there are any {@link ExerciseSolution} in this {@link Exam} which are not done yet.
   * If there is no Exam with this id, true will be returned.
   *
   * @return true if all ExerciseSolutions are done (isDone = true)
   */
  public boolean isFinished(long examId);
}
