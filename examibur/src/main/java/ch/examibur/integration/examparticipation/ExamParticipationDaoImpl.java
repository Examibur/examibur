package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExamParticipationDaoImpl implements ExamParticipationDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamParticipationDaoImpl.class);

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExamParticipationDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public List<ExamParticipation> getExamParticipations(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      return getExamParticipations(examId, entityManager);
    } catch (Exception e) {
      LOGGER.error("Error occured during getExamParticipations call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<ExamParticipation> getExamParticipations(long examId, EntityManager entityManager) {
    TypedQuery<ExamParticipation> examParticipationsQuery = entityManager.createQuery(
        "SELECT ep FROM ExamParticipation ep WHERE ep.exam.id = :examId ORDER BY ep.id",
        ExamParticipation.class);
    return examParticipationsQuery.setParameter("examId", examId).getResultList();
  }

  @Override
  public ExamParticipation getExamParticipation(long examParticipationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      ExamParticipation result = entityManager.find(ExamParticipation.class, examParticipationId);
      if (result == null) {
        throw new NoResultException(
            "ExamParticipation with id " + examParticipationId + " not found.");
      }
      return result;
    } finally {
      entityManager.close();
    }
  }

}
