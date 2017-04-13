package ch.examibur.integration.exercise;

import ch.examibur.integration.utils.EntityManagerHelper;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseDaoImpl implements ExerciseDao {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseDaoImpl.class);

  @Override
  public double getMaxPoints(long examId) {
    EntityManager entityManager = EntityManagerHelper.INSTANCE.createEntityManager();
    try {
      TypedQuery<Double> maxPointsQuery = entityManager
          .createQuery("SELECT COALESCE(SUM(e.maxPoints), 0) "
              + "FROM Exercise e WHERE e.exam.id = :examId", 
              Double.class);
      return maxPointsQuery.setParameter("examId", examId).getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExam call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
