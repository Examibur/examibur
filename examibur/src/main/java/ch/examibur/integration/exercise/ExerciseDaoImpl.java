package ch.examibur.integration.exercise;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseDaoImpl implements ExerciseDao {

  private final Logger logger = LoggerFactory.getLogger(ExerciseDaoImpl.class);
  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExerciseDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public double getMaxPoints(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Double> maxPointsQuery = entityManager.createQuery(
          "SELECT COALESCE(SUM(e.maxPoints), 0) " + "FROM Excercise e WHERE e.exam.id = :examId",
          Double.class);
      return maxPointsQuery.setParameter("examId", examId).getSingleResult();
    } catch (Exception e) {
      logger.error("Error occured during getExam call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
