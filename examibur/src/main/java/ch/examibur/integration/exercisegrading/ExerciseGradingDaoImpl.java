package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExerciseGradingDaoImpl implements ExerciseGradingDao {

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExerciseGradingDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public ExerciseGrading getGradingCreatedInState(long exerciseSolutionId, ExamState state) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseGrading> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT eg FROM ExerciseGrading eg WHERE "
              + "eg.exerciseSolution.id = :exerciseSolutionId and eg.createdInState = :state",
          ExerciseGrading.class);
      List<ExerciseGrading> result = exerciseSolutionQuery
          .setParameter("exerciseSolutionId", exerciseSolutionId).setParameter("state", state)
          .getResultList();
      return !result.isEmpty() ? result.get(0) : null;
    } finally {
      entityManager.close();
    }
  }

}
