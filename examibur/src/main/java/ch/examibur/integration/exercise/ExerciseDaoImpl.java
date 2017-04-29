package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExerciseDaoImpl implements ExerciseDao {

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExerciseDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {

      TypedQuery<Exercise> exercisesQuery = entityManager.createQuery(
          "SELECT e FROM Exercise e WHERE e.exam.id = :examId",
          Exercise.class);
      return exercisesQuery.setParameter("examId", examId).getResultList();
    } finally {
      entityManager.close();
    }
  }

}
