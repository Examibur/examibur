package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingDaoImpl implements ExerciseGradingDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseGradingDaoImpl.class);
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

    } catch (Exception e) {
      LOGGER.error("Error occured during getGradingsForExerciseSolution call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getTotalPointsOfExamGradings(long examId, long participationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Double> totalPointsOfExamGradingsQuery = entityManager
          .createQuery("SELECT SUM(eg.points) FROM ExerciseGrading eg "
              + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
              + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
              + "INNER JOIN Exam e ON ep.exam.id = e.id WHERE eg.isFinalGrading = true "
              + "AND e.id = :examId AND ep.participant.id = :participationId", Double.class);
      return totalPointsOfExamGradingsQuery.setParameter("examId", examId)
          .setParameter("participationId", participationId).getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Error occured during getTotalPointsOfExamGradings call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getProgressOfExamGradings(long examId, long participationId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      Query progressOfExamGradingsQuery = entityManager
          .createQuery("SELECT COUNT(NULLIF(es.isDone, false)), "
              + "COUNT(es.isDone) FROM ExerciseGrading eg "
              + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
              + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
              + "INNER JOIN Exam e ON ep.exam.id = e.id WHERE eg.isFinalGrading = true "
              + "AND e.id = :examId AND ep.participant.id = :participationId")
          .setParameter("examId", examId).setParameter("participationId", participationId);
      Object[] result = (Object[]) progressOfExamGradingsQuery.getSingleResult();
      long sumOfCompletedExamGradings = (long) result[0];
      long sumOfExamGradings = (long) result[1];
      return (double) sumOfCompletedExamGradings / sumOfExamGradings;
    } catch (Exception e) {
      LOGGER.error("Error occured during getProgressOfExamGradings call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
