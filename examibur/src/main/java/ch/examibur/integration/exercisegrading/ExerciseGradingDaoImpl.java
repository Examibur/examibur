package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
  public double getAveragePointsOfExercise(long exerciseId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Double> totalPointsOfExamGradingsQuery = entityManager
          .createQuery(
              "SELECT AVG(eg.points) FROM ExerciseGrading eg "
                  + "INNER JOIN ExerciseSolution es ON eg.exerciseSolution.id = es.id "
                  + "INNER JOIN ExamParticipation ep ON es.participation.id = ep.id "
                  + "WHERE eg.isFinalGrading = true AND es.exercise.id = :exerciseId",
              Double.class);
      return totalPointsOfExamGradingsQuery.setParameter("exerciseId", exerciseId)
          .getSingleResult();
    } finally {
      entityManager.close();
    }
  }

}
