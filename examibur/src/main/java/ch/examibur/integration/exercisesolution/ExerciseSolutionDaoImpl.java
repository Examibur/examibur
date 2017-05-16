package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.Exercise;
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
  public ExerciseSolution getFirstExerciseSolution(BrowseSolutionsMode mode, long resourceId)
      throws ExamiburException {
    switch (mode) {
      case BY_EXERCISE:
        return getNextExerciseSolutionByExercise(0, resourceId);
      case BY_EXERCISES:
        Exercise exercise = getExerciseById(resourceId);
        return getNextExerciseSolutionByExercises(exercise.getExam().getId(), resourceId);
      case BY_PARTICIPATION:
        return getNextExerciseSolutionByParticipation(resourceId, 0);
      case BY_PARTICIPATIONS:
        ExamParticipation participation = getExamParticipationById(resourceId);
        return getNextExerciseSolutionByParticipations(participation.getExam().getId(), resourceId);
      default:
        InvalidParameterException invalidParameterException = new InvalidParameterException(
            "BrowseSolutionsMode with value '" + mode.toString() + "' is not defined");
        LOGGER.error(invalidParameterException.getMessage(), invalidParameterException);
        throw invalidParameterException;
    }
  }

  private ExamParticipation getExamParticipationById(long participationId) {
    // TODO auslagern (siehe ExamReportDaoImpl)
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<ExamParticipation> participationQuery = entityManager.createQuery(
          "SELECT p FROM ExamParticipation p WHERE p.id = :participationId",
          ExamParticipation.class);
      return participationQuery.setParameter("participationId", participationId).getSingleResult();
    } finally {
      entityManager.close();
    }
  }

  private Exercise getExerciseById(long exerciseId) {
    // TODO auslagern (siehe ExamReportDaoImpl)
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Exercise> exerciseQuery = entityManager
          .createQuery("SELECT e FROM Exercise e WHERE e.id = :exerciseId", Exercise.class);
      return exerciseQuery.setParameter("exerciseId", exerciseId).getSingleResult();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExerciseSolution getNextExerciseSolution(BrowseSolutionsMode mode,
      long currentExerciseSolutionId) throws ExamiburException {
    ExerciseSolution currentExerciseSolution = getExerciseSolution(currentExerciseSolutionId);
    switch (mode) {
      case BY_EXERCISE:
        return getNextExerciseSolutionByExercise(currentExerciseSolution.getParticipation().getId(),
            currentExerciseSolution.getExercise().getId());
      case BY_EXERCISES:
        return getNextExerciseSolutionByExercises(
            currentExerciseSolution.getParticipation().getExam().getId(),
            currentExerciseSolution.getExercise().getId());
      case BY_PARTICIPATION:
        return getNextExerciseSolutionByParticipation(
            currentExerciseSolution.getParticipation().getId(),
            currentExerciseSolution.getExercise().getId());
      case BY_PARTICIPATIONS:
        return getNextExerciseSolutionByParticipations(
            currentExerciseSolution.getParticipation().getExam().getId(),
            currentExerciseSolution.getParticipation().getId());
      default:
        InvalidParameterException invalidParameterException = new InvalidParameterException(
            "BrowseSolutionsMode with value '" + mode.toString() + "' is not defined");
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

  private ExerciseSolution getNextExerciseSolutionByExercise(long participationId,
      long exerciseId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.exercise.id = :searchId "
        + "AND e.participation.id > :filterId ORDER BY e.participation.id";
    return getNextExerciseSolution(exerciseId, participationId, query);
  }

  private ExerciseSolution getNextExerciseSolutionByExercises(long examId, long exerciseId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.participation.exam.id = :searchId AND e.exercise.id >= :filterId "
        + "ORDER BY e.exercise.id, e.participation.id";
    return getNextExerciseSolution(examId, exerciseId, query);
  }

  private ExerciseSolution getNextExerciseSolutionByParticipation(long participationId,
      long exerciseId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.participation.id = :searchId "
        + "AND e.exercise.id > :filterId ORDER BY e.exercise.id";
    return getNextExerciseSolution(participationId, exerciseId, query);
  }

  private ExerciseSolution getNextExerciseSolutionByParticipations(long examId,
      long participationId) {
    String query = "SELECT e FROM ExerciseSolution e WHERE e.isDone = false "
        + "AND e.participation.exam.id = :searchId AND e.participation.id >= :filterId "
        + "ORDER BY e.participation.id, e.exercise.id";
    return getNextExerciseSolution(examId, participationId, query);
  }
}
