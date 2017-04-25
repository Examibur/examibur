package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
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
      TypedQuery<ExamParticipation> examParticipationsQuery = entityManager.createQuery(
          "SELECT ep FROM ExamParticipation ep WHERE ep.exam.id = :examId",
          ExamParticipation.class);
      return examParticipationsQuery.setParameter("examId", examId).getResultList();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExamParticipations call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
