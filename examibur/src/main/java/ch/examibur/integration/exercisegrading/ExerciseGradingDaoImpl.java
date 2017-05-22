package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.User;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.exception.ValidationException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingDaoImpl implements ExerciseGradingDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseGradingDao.class);

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

  @Override
  public double getTotalPointsOfExamGradings(long examParticipationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      return getTotalPointsOfExamGradings(examParticipationId, entityManager);
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getTotalPointsOfExamGradings(long examParticipationId,
      EntityManager entityManager) {

    TypedQuery<Double> totalPointsOfExamGradingsQuery = entityManager
        .createQuery("SELECT COALESCE(SUM(eg.points), 0) FROM ExerciseGrading eg "
            + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
            + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
            + "WHERE eg.isFinalGrading = true AND ep.id = :examParticipationId", Double.class);
    return totalPointsOfExamGradingsQuery.setParameter("examParticipationId", examParticipationId)
        .getSingleResult();

  }

  @Override
  public Optional<Double> getPointsOfExerciseSolution(long exerciseSolutionId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Double> totalPointsOfExerciseSolutionQuery = entityManager
          .createQuery("SELECT eg.points FROM ExerciseGrading eg "
              + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = :exerciseSolutionId "
              + "WHERE eg.isFinalGrading = true", Double.class);
      List<Double> resultList = totalPointsOfExerciseSolutionQuery
          .setParameter("exerciseSolutionId", exerciseSolutionId).getResultList();
      return !resultList.isEmpty() ? Optional.of(resultList.get(0)) : Optional.empty();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getProgressOfExamGradings(long examId, long participationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Long> getCountOfGradingsQuery = entityManager
          .createQuery("SELECT COUNT(DISTINCT es.exercise.id) FROM ExerciseGrading eg "
              + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
              + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
              + "WHERE es.isDone = true AND ep.id = :participationId", Long.class);

      TypedQuery<Long> getCountOfExercisesQuery = entityManager.createQuery(
          "SELECT COUNT(ex.id) from Exercise ex where ex.exam.id = :examId", Long.class);

      long countOfGradings = getCountOfGradingsQuery
          .setParameter("participationId", participationId).getSingleResult();
      long countOfExercises = getCountOfExercisesQuery.setParameter("examId", examId)
          .getSingleResult();
      return (double) countOfGradings / countOfExercises;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getAveragePointsOfExercise(long exerciseId, EntityManager entityManager) {
    TypedQuery<Double> avgPointsOfExerciseQuery = entityManager
        .createQuery("SELECT AVG(eg.points) FROM ExerciseGrading eg "
            + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
            + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
            + "WHERE eg.isFinalGrading = true AND es.exercise.id = :exerciseId", Double.class);
    return avgPointsOfExerciseQuery.setParameter("exerciseId", exerciseId).getSingleResult();
  }

  @Override
  public boolean checkIfAllExercisesAreGraded(long examId, long participationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      return checkIfAllExercisesAreGraded(examId, participationId, entityManager);
    } finally {
      entityManager.close();
    }
  }

  @Override
  public boolean checkIfAllExercisesAreGraded(long examId, long participationId,
      EntityManager entityManager) {
    TypedQuery<Long> getExerciseIdsOfGradingsQuery = entityManager
        .createQuery("SELECT DISTINCT es.exercise.id FROM ExerciseGrading eg "
            + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
            + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
            + "WHERE ep.id = :participationId", Long.class);
    List<Long> exerciseIdsOfGradings = getExerciseIdsOfGradingsQuery
        .setParameter("participationId", participationId).getResultList();

    TypedQuery<Long> getExerciseIdsQuery = entityManager
        .createQuery("SELECT ex.id from Exercise ex where ex.exam.id = :examId", Long.class);
    List<Long> exerciseIds = getExerciseIdsQuery.setParameter("examId", examId).getResultList();

    return exerciseIdsOfGradings.equals(exerciseIds);
  }

  @Override
  public void addGrading(long exerciseSolutionId, String comment, String reasoning, double points,
      User gradingAuthor) throws ExamiburException {
    EntityManager entityManager = entityManagerProvider.get();

    try {
      entityManager.getTransaction().begin();

      ExerciseSolution exerciseSolution = entityManager.find(ExerciseSolution.class,
          exerciseSolutionId);
      if (exerciseSolution == null) {
        throw new NoResultException();
      }
      checkIfPointsInRange(exerciseSolution, points);

      ExamState examState = exerciseSolution.getParticipation().getExam().getState();
      if (examState != ExamState.CORRECTION && examState != ExamState.REVIEW) {
        throw new IllegalOperationException(
            "Not possible to add a new grading in exam state " + examState.toString());
      }

      checkIfGradingAlreadyIsDone(exerciseSolution, examState);
      exerciseSolution.setDone(true);

      ExerciseGrading exerciseGrading = new ExerciseGrading(
          new Date(Calendar.getInstance().getTime().getTime()), comment, reasoning, points,
          examState, true, gradingAuthor, exerciseSolution);
      entityManager.persist(exerciseGrading);

      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      LOGGER.error("Error while adding new grading to exerciseSolution " + exerciseSolutionId, ex);
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public void setFinalGrading(long exerciseGradingId, boolean isFinal) throws NotFoundException {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      entityManager.getTransaction().begin();
      ExerciseGrading grading = entityManager.find(ExerciseGrading.class, exerciseGradingId);
      if (grading == null) {
        NotFoundException ex = new NotFoundException(
            "exerciseGrading with id " + exerciseGradingId + " does not exist");
        LOGGER.error(ex.getMessage(), ex);
        throw ex;
      }
      grading.setFinalGrading(isFinal);
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  private void checkIfPointsInRange(ExerciseSolution exerciseSolution, double points)
      throws ExamiburException {
    if (points > exerciseSolution.getExercise().getMaxPoints() || points < 0) {
      ValidationException validationException = new ValidationException(
          points + " points are not allowed for this ExerciseSolution (id = "
              + exerciseSolution.getId() + ")");
      LOGGER.error(validationException.getMessage(), validationException);
      throw validationException;
    }
  }

  private void checkIfGradingAlreadyIsDone(ExerciseSolution exerciseSolution, ExamState examState)
      throws IllegalOperationException {
    if (exerciseSolution.isDone()) {
      IllegalOperationException illegalOperationException = new IllegalOperationException(
          examState.toString() + " is done already for the exerciseSolution "
              + exerciseSolution.getId());
      LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
      throw illegalOperationException;
    }
  }

}
