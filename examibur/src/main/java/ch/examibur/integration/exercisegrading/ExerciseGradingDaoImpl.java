package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.domain.User;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.ValidationException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
      TypedQuery<Double> totalPointsOfExamGradingsQuery = entityManager
          .createQuery(
              "SELECT SUM(eg.points) FROM ExerciseGrading eg "
                  + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
                  + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
                  + "WHERE eg.isFinalGrading = true AND ep.id = :examParticipationId",
              Double.class);
      return totalPointsOfExamGradingsQuery.setParameter("examParticipationId", examParticipationId)
          .getSingleResult();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getProgressOfExamGradings(long examParticipationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      Query progressOfExamGradingsQuery = entityManager
          .createQuery("SELECT COUNT(NULLIF(es.isDone, false)), "
              + "COUNT(es.isDone) FROM ExerciseGrading eg "
              + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
              + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
              + "WHERE eg.isFinalGrading = true AND ep.id = :examParticipationId")
          .setParameter("examParticipationId", examParticipationId);
      Object[] result = (Object[]) progressOfExamGradingsQuery.getSingleResult();
      long sumOfCompletedExamGradings = (long) result[0];
      long sumOfExamGradings = (long) result[1];
      return (double) sumOfCompletedExamGradings / sumOfExamGradings;
    } finally {
      entityManager.close();
    }
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
