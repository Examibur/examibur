package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseGrading;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
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
      TypedQuery<Double> totalPointsOfExamGradingsQuery = entityManager.createQuery(
          "select SUM(eg.points) from ExerciseGrading eg "
          + "inner join ExerciseSolution es on eg.exerciseSolution.id = es.id "
          + "inner join ExamParticipation ep on es.participation.id = ep.id "
          + "inner join Exam e on ep.exam.id = e.id "
          + "where eg.isFinalGrading = true and e.id = :examId "
          + "and ep.participant.id = :participationId",
              Double.class);
      return totalPointsOfExamGradingsQuery.setParameter("examId", examId)
          .setParameter("participationId", participationId)
          .getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Error occured during getTotalPointsOfExamGradings call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }
  
}
