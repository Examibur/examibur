package ch.examibur.integration.exercisegrading;

import ch.examibur.domain.ExerciseGrading;
import ch.examibur.integration.utils.EntityManagerHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseGradingDaoImpl implements ExerciseGradingDao {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseGradingDaoImpl.class);


  @Override
  public List<ExerciseGrading> getGradingsForExerciseSolution(long exerciseSolutionId) {
    EntityManager entityManager = EntityManagerHelper.INSTANCE.createEntityManager();
    try {
      TypedQuery<ExerciseGrading> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT eg FROM ExerciseGrading eg WHERE eg.exerciseSolution.id = :exerciseSolutionId",
          ExerciseGrading.class);
      return exerciseSolutionQuery.setParameter("exerciseSolutionId", exerciseSolutionId)
          .getResultList();
    } catch (Exception e) {
      LOGGER.error("Error occured during getGradingsForExerciseSolution call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
