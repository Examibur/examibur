package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.aggregation.BrowseSolutionsMode;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
      ExerciseSolution exerciseSolution =
          entityManager.find(ExerciseSolution.class, exerciseSolutionId);
      if (exerciseSolution == null) {
        throw new NoResultException(
            "ExerciseSolution with id " + exerciseSolutionId + " not found.");
      }
      return exerciseSolution;

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
          "SELECT es FROM ExerciseSolution es WHERE es.participation.id = :examParticipationId"
              + " ORDER BY es.exercise.orderInExam",
          ExerciseSolution.class);
      return exerciseSolutionQuery.setParameter("examParticipationId", examParticipationId)
          .getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<ExerciseSolution> getExerciseSolutionsForExercise(long exerciseId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> exerciseSolutionQuery = entityManager
          .createQuery("SELECT es FROM ExerciseSolution es WHERE es.exercise.id = :exerciseId "
              + "ORDER BY es.id", ExerciseSolution.class);
      return exerciseSolutionQuery.setParameter("exerciseId", exerciseId).getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public void setIsDone(long exerciseSolutionId, boolean isDone) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      entityManager.getTransaction().begin();
      ExerciseSolution solution = entityManager.find(ExerciseSolution.class, exerciseSolutionId);
      if (solution == null) {
        throw new NoResultException(
            "exerciseSolution with id " + exerciseSolutionId + " does not exist");
      }
      solution.setDone(isDone);
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode browseMode, long examId,
      long queryResourceId, long exerciseSolutionId) {
    long participantId =
        browseMode.equals(BrowseSolutionsMode.BY_PARTICIPATION) ? queryResourceId : 0;
    long exerciseId = browseMode.equals(BrowseSolutionsMode.BY_EXERCISE) ? queryResourceId : 0;
    if (exerciseSolutionId > 0) {
      ExerciseSolution currentExerciseSolution = getExerciseSolution(exerciseSolutionId);
      participantId = currentExerciseSolution.getParticipation().getId();
      exerciseId = currentExerciseSolution.getExercise().getId();
    }

    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery =
          buildQuery(entityManager, browseMode, examId, participantId, exerciseId);
      // can't use getSingleResult() because null should also be possible
      List<ExerciseSolution> resultList =
          nextExerciseSolutionQuery.setMaxResults(1).getResultList();
      if (resultList.isEmpty()) {
        return null;
      }
      return resultList.get(0);
    } finally {
      entityManager.close();
    }
  }

  private TypedQuery<ExerciseSolution> buildQuery(EntityManager entityManager,
      BrowseSolutionsMode browseMode, long examId, long participantId, long exerciseId) {
    TypedQuery<ExerciseSolution> nextExerciseSolutionQuery;
    String query = getQueryString(browseMode);
    nextExerciseSolutionQuery = entityManager.createQuery(query, ExerciseSolution.class);
    nextExerciseSolutionQuery.setParameter("exerciseId", exerciseId).setParameter("participantId",
        participantId);
    if (browseMode.equals(BrowseSolutionsMode.BY_EXERCISES)
        || browseMode.equals(BrowseSolutionsMode.BY_PARTICIPATIONS)) {
      nextExerciseSolutionQuery.setParameter("examId", examId);
    }
    return nextExerciseSolutionQuery;
  }

  private String getQueryString(BrowseSolutionsMode browseMode) {
    String baseCondition = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false AND ";
    String exerciseCondition =
        "e.exercise.id = :exerciseId AND e.participation.id > :participantId";
    String participantCondition =
        "e.participation.id = :participantId AND e.exercise.id > :exerciseId";
    switch (browseMode) {
      case BY_EXERCISE:
        return baseCondition + exerciseCondition + " ORDER BY e.participation.id";
      case BY_EXERCISES:
        return baseCondition + "((" + exerciseCondition + ") "
            + "OR (e.participation.exam.id = :examId AND e.exercise.id > :exerciseId)) "
            + "ORDER BY e.exercise.id, e.participation.id";
      case BY_PARTICIPATION:
        return baseCondition + participantCondition + " ORDER BY e.exercise.id";
      case BY_PARTICIPATIONS:
        return baseCondition + "((" + participantCondition + ") "
            + "OR (e.participation.exam.id = :examId AND e.participation.id > :participantId)) "
            + "ORDER BY e.participation.id, e.exercise.id";
      default:
        throw new IllegalArgumentException(new IllegalArgumentException(
            "BrowseSolutionsMode with value '" + browseMode.toString() + "' is not defined"));
    }
  }
}
