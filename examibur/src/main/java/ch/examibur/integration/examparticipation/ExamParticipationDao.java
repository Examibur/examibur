package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public interface ExamParticipationDao {

  /**
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}. Throws a {@link NoResultException} if it
   *          doesn't exist.
   * @return the {@link ExamParticipation} with the given id. Must be positive.
   */
  public ExamParticipation getExamParticipation(long examParticipationId);

  /**
   * @param examId
   *          the id of exam
   * @return all exam participations for the specified exam.
   */
  public List<ExamParticipation> getExamParticipations(long examId);

  /**
   * @see ch.examibur.integration.examparticipation.ExamParticipationDao#getExamParticipations(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  public List<ExamParticipation> getExamParticipations(long examId, EntityManager entityManager);

}
