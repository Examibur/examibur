package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseDaoImpl implements ExerciseDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseDaoImpl.class);
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
          "SELECT COALESCE(SUM(e.maxPoints), 0) FROM Exercise e WHERE e.exam.id = :examId",
          Double.class);
      return maxPointsQuery.setParameter("examId", examId).getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExam call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {

      TypedQuery<Exercise> exercisesQuery = entityManager.createQuery(
          "SELECT e FROM Exercise e WHERE e.exam.id = :examId",
          Exercise.class);
      return exercisesQuery.setParameter("examId", examId).getResultList();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExercises call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
