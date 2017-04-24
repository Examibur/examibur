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
  public ExerciseSolution getExerciseSolutionFromNextParticipation(
      long currentExerciseSolutionId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery = entityManager.createQuery(
          "SELECT e FROM ExerciseSolution e WHERE e.isDone = false AND e.exercise.id = ( "
              + "SELECT e1.exercise.id FROM ExerciseSolution e1 WHERE e1.id = :currentExerciseSolutionId ) "
              + "AND e.participation.id > ( "
              + "SELECT e2.participation.id FROM ExerciseSolution e2 WHERE e2.id = :currentExerciseSolutionId ) "
              + "ORDER BY e.id",
          ExerciseSolution.class);
      List<ExerciseSolution> resultList = nextExerciseSolutionQuery
          .setParameter("currentExerciseSolutionId", currentExerciseSolutionId).getResultList();
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
