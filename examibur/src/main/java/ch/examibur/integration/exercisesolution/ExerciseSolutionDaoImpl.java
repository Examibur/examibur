package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExerciseSolution;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExerciseSolutionDaoImpl implements ExerciseSolutionDao {

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExerciseSolutionDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public ExerciseSolution getExerciseSolution(long exerciseSolutionId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT es FROM ExerciseSolution es WHERE es.id = :exerciseSolutionId",
          ExerciseSolution.class);
      return exerciseSolutionQuery.setParameter("exerciseSolutionId", exerciseSolutionId)
          .getSingleResult();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.exercise.id = :exerciseId "
        + "AND e.participation.id > :participationId ORDER BY e.participation.id";
    return getNextExerciseSolution(currentExerciseSolutionId, query);
  }

  @Override
  public ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.participation.id = :participationId "
        + "AND e.exercise.id > :exerciseId ORDER BY e.exercise.id";
    return getNextExerciseSolution(currentExerciseSolutionId, query);
  }

  private ExerciseSolution getNextExerciseSolution(long currentExerciseSolutionId, String query) {
    // check if ExerciseSolution exists, throws a NoResultException if it doesn't.
    ExerciseSolution currentExerciseSolution = getExerciseSolution(currentExerciseSolutionId);

    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery = entityManager.createQuery(query,
          ExerciseSolution.class);
      // can't use getSingleResult() because null should also be possible
      List<ExerciseSolution> resultList = nextExerciseSolutionQuery
          .setParameter("exerciseId", currentExerciseSolution.getExercise().getId())
          .setParameter("participationId", currentExerciseSolution.getParticipation().getId())
          .setMaxResults(1).getResultList();
      if (resultList.isEmpty()) {
        return null;
      }
      return resultList.get(0);
    } finally {
      entityManager.close();
    }
  }
}
