package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import java.util.List;
import javax.persistence.NoResultException;

public interface ExamParticipationDao {

  public List<ExamParticipation> getExamParticipations(long examId);

  /**
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}. Throws a {@link NoResultException} if it
   *          doesn't exist.
   * @return the {@link ExamParticipation} with the given id. Must be positive.
   */
  public ExamParticipation getExamParticipation(long examParticipationId);
}
