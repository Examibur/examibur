package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
      return getExercises(examId, entityManager);
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<Exercise> getExercises(long examId, EntityManager entityManager) {
    TypedQuery<Exercise> exercisesQuery = entityManager.createQuery(
        "SELECT e FROM Exercise e WHERE e.exam.id = :examId ORDER BY e.orderInExam",
        Exercise.class);
    return exercisesQuery.setParameter("examId", examId).getResultList();
  }

  @Override
  public Exercise getExercise(long exerciseId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      Exercise exercise = entityManager.find(Exercise.class, exerciseId);
      if (exercise == null) {
        throw new NoResultException("Exercise with id " + exerciseId + " not found.");
      }
      return exercise;
    } finally {
      entityManager.close();
    }
  }

}
