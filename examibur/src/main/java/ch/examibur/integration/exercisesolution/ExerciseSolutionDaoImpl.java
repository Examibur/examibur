package ch.examibur.integration.exercisesolution;

import ch.examibur.domain.ExerciseSolution;
import ch.examibur.integration.SingleResultNotFoundException;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    } catch (NoResultException e) {
      String message = "ExerciseSolution with id " + exerciseSolutionId + " not found";
      LOGGER.error(message);
      throw new SingleResultNotFoundException(message, e);
    } catch (Exception e) {
      LOGGER.error("Error occured during getExerciseSolution call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
