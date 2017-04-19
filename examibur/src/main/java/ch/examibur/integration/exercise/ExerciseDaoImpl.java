package ch.examibur.integration.exercise;

import ch.examibur.domain.Exam;
import com.google.inject.Inject;
import com.google.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExerciseDaoImpl implements ExerciseDao {

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExerciseDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public double getMaxPoints(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      // check if exam exists, throws an exception if it doesn't
      TypedQuery<Exam> examQuery = entityManager
          .createQuery("SELECT e.id FROM Exam e WHERE e.id = :examId", Exam.class);
      examQuery.setParameter("examId", examId).getSingleResult();

      TypedQuery<Double> maxPointsQuery = entityManager.createQuery(
          "SELECT COALESCE(SUM(e.maxPoints), 0) " + "FROM Exercise e WHERE e.exam.id = :examId",
          Double.class);
      return maxPointsQuery.setParameter("examId", examId).getSingleResult();
    } finally {
      entityManager.close();
    }
  }

}
