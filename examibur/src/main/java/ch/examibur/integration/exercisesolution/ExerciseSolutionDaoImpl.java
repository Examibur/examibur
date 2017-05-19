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
  public ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode browseMode, long examId,
      long queryResourceId, long exerciseSolutionId) throws ExamiburException {
    long participantId = browseMode.equals(BrowseSolutionsMode.BY_PARTICIPATION) ? queryResourceId
        : 0;
    long exerciseId = browseMode.equals(BrowseSolutionsMode.BY_EXERCISE) ? queryResourceId : 0;
    if (exerciseSolutionId > 0) {
      ExerciseSolution currentExerciseSolution = getExerciseSolution(exerciseSolutionId);
      participantId = currentExerciseSolution.getParticipation().getId();
      exerciseId = currentExerciseSolution.getExercise().getId();
    }

    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false ";
    switch (browseMode) {
      case BY_EXERCISE:
        query += "AND e.exercise.id = :searchId "
            + "AND e.participation.id > :filterId ORDER BY e.participation.id";
        return getNextExerciseSolution(exerciseId, participantId, query);
      case BY_EXERCISES:
        query += "AND e.participation.exam.id = :searchId AND e.exercise.id >= :filterId "
            + "ORDER BY e.exercise.id, e.participation.id";
        return getNextExerciseSolution(examId, exerciseId, query);
      case BY_PARTICIPATION:
        query += "AND e.participation.id = :searchId "
            + "AND e.exercise.id > :filterId ORDER BY e.exercise.id";
        return getNextExerciseSolution(participantId, exerciseId, query);
      case BY_PARTICIPATIONS:
        query += "AND e.participation.exam.id = :searchId AND e.participation.id >= :filterId "
            + "ORDER BY e.participation.id, e.exercise.id";
        return getNextExerciseSolution(examId, participantId, query);
      default:
        InvalidParameterException invalidParameterException = new InvalidParameterException(
            "BrowseSolutionsMode with value '" + browseMode.toString() + "' is not defined");
        LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
        throw invalidParameterException;
    }
  }

  private ExerciseSolution getNextExerciseSolution(long searchId, long filterId, String query) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExerciseSolution> nextExerciseSolutionQuery = entityManager.createQuery(query,
          ExerciseSolution.class);
      // can't use getSingleResult() because null should also be possible
      List<ExerciseSolution> resultList = nextExerciseSolutionQuery
          .setParameter("searchId", searchId).setParameter("filterId", filterId).setMaxResults(1)
          .getResultList();
      if (resultList.isEmpty()) {
        return null;
      }
      return resultList.get(0);
    } finally {
      entityManager.close();
    }
  }
}
