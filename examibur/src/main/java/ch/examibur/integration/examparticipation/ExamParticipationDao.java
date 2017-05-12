package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public interface ExamParticipationDao {

  /**
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}. Throws a {@link NoResultException} if it
   *          doesn't exist.
   * @return the {@link ExamParticipation} with the given id. Must be positive.
   */
  ExamParticipation getExamParticipation(long examParticipationId);

  /**
   * @param examId
   *          the id of exam
   * @return all exam participations for the specified exam.
   */
  List<ExamParticipation> getExamParticipations(long examId);

  /**
   * @see ch.examibur.integration.examparticipation.ExamParticipationDao#getExamParticipations(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  List<ExamParticipation> getExamParticipations(long examId, EntityManager entityManager);

}
