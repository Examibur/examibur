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
    // check if ExerciseSolution exists, throws a NoResultException if it doesn't.
    ExerciseSolution currentExerciseSolution = getExerciseSolution(currentExerciseSolutionId);

    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery = entityManager
          .createQuery(
              "SELECT e FROM ExerciseSolution e WHERE e.isDone = false AND e.exercise.id = :exerciseId "
                  + "AND e.participation.id > :participationId ORDER BY e.id",
              ExerciseSolution.class);
      List<ExerciseSolution> resultList = nextExerciseSolutionQuery
          .setParameter("exerciseId", currentExerciseSolution.getExercise().getId())
          .setParameter("participationId", currentExerciseSolution.getParticipation().getId())
          .getResultList();
      if (!resultList.isEmpty()) {
        return resultList.get(0);
      } else {
        return null;
      }
    } finally {
      entityManager.close();
    }
  }
}
