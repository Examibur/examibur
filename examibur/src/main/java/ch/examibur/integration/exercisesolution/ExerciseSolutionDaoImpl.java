package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseSolutionDaoImpl implements ExerciseSolutionDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseSolutionDaoImpl.class);

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
          "SELECT es FROM ExerciseSolution es WHERE es.participation.id = :examParticipationId ORDER BY es.exercise.orderInExam",
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
      TypedQuery<ExerciseSolution> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT es FROM ExerciseSolution es WHERE es.exercise.id = :exerciseId ORDER BY es.id",
          ExerciseSolution.class);
      return exerciseSolutionQuery.setParameter("exerciseId", exerciseId)
          .getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode browseMode, long examId,
      long queryResourceId, long exerciseSolutionId) throws ExamiburException {
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
      BrowseSolutionsMode browseMode, long examId, long participantId, long exerciseId)
      throws ExamiburException {
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

  private String getQueryString(BrowseSolutionsMode browseMode) throws ExamiburException {
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
        InvalidParameterException invalidParameterException = new InvalidParameterException(
            "BrowseSolutionsMode with value '" + browseMode.toString() + "' is not defined");
        LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
        throw invalidParameterException;
    }
  }
}
