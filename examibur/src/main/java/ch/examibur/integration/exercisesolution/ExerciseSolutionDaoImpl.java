package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
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
  public List<ExerciseSolution> getExerciseSolutionsForExamParticipation(long examParticipationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      // check if examParticipation exists, throw an exception if it doesn't
      TypedQuery<ExamParticipation> examParticipationQuery = entityManager.createQuery(
          "SELECT e.id FROM ExamParticipation e WHERE e.id = :examParticipationId",
          ExamParticipation.class);
      examParticipationQuery.setParameter("examParticipationId", examParticipationId)
          .getSingleResult();

      TypedQuery<ExerciseSolution> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT es FROM ExerciseSolution es WHERE es.participation.id = :examParticipationId",
          ExerciseSolution.class);
      return exerciseSolutionQuery.setParameter("examParticipationId", examParticipationId)
          .getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExerciseSolution getFirstExerciseSolutionFromParticipation(long participationId) {
    return getNextExerciseSolutionFromParticipation(participationId, 0);
  }

  @Override
  public ExerciseSolution getFirstExerciseSolutionFromExercise(long exerciseId) {
    return getExerciseSolutionFromNextParticipation(0, exerciseId);
  }

  @Override
  public ExerciseSolution getExerciseSolutionFromNextParticipation(long currentExerciseSolutionId) {
    ExerciseSolution currentExerciseSolution = getExerciseSolution(currentExerciseSolutionId);
    return getExerciseSolutionFromNextParticipation(
        currentExerciseSolution.getParticipation().getId(),
        currentExerciseSolution.getExercise().getId());
  }

  @Override
  public ExerciseSolution getNextExerciseSolutionFromParticipation(long currentExerciseSolutionId) {
    ExerciseSolution currentExerciseSolution = getExerciseSolution(currentExerciseSolutionId);
    return getNextExerciseSolutionFromParticipation(
        currentExerciseSolution.getParticipation().getId(),
        currentExerciseSolution.getExercise().getId());
  }

  private ExerciseSolution getExerciseSolutionFromNextParticipation(long participationId,
      long exerciseId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.exercise.id = :exerciseId "
        + "AND e.participation.id > :participationId ORDER BY e.participation.id";
    return getNextExerciseSolution(participationId, exerciseId, query);
  }

  private ExerciseSolution getNextExerciseSolutionFromParticipation(long participationId,
      long exerciseId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.participation.id = :participationId "
        + "AND e.exercise.id > :exerciseId ORDER BY e.exercise.id";
    return getNextExerciseSolution(participationId, exerciseId, query);
  }

  private ExerciseSolution getNextExerciseSolution(long participationId, long exerciseId,
      String query) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery = entityManager.createQuery(query,
          ExerciseSolution.class);
      // can't use getSingleResult() because null should also be possible
      List<ExerciseSolution> resultList = nextExerciseSolutionQuery
          .setParameter("exerciseId", exerciseId).setParameter("participationId", participationId)
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
