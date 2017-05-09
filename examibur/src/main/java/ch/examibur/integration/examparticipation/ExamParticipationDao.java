package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import java.util.List;
import javax.persistence.EntityManager;

public interface ExamParticipationDao {
  
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
