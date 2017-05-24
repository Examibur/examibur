package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.domain.ExerciseSolution;
import ch.examibur.service.exception.NotFoundException;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExamDaoImpl implements ExamDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamDaoImpl.class);

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public ExamDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Exam> examsForAuthorQuery = entityManager.createQuery(
          "SELECT e FROM Exam e WHERE e.author.id = :authorId ORDER BY e.id", Exam.class);
      return examsForAuthorQuery.setParameter("authorId", authorId).getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<Exam> getExamsForAuthor(long authorId, ExamState state) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Exam> examsForAuthorQuery =
          entityManager.createQuery("SELECT e FROM Exam e WHERE e.author.id = :authorId "
              + "AND e.state = :examState ORDER BY e.id", Exam.class);
      examsForAuthorQuery.setParameter("authorId", authorId);
      examsForAuthorQuery.setParameter("examState", state);
      return examsForAuthorQuery.getResultList();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public Exam getExam(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Exam> examQuery =
          entityManager.createQuery("SELECT e FROM Exam e WHERE e.id = :examId", Exam.class);
      return examQuery.setParameter("examId", examId).getSingleResult();
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getMaxPoints(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      return getMaxPoints(examId, entityManager);
    } finally {
      entityManager.close();
    }
  }

  @Override
  public double getMaxPoints(long examId, EntityManager entityManager) {
    // check if exam exists, throws an exception if it doesn't
    TypedQuery<Exam> examQuery =
        entityManager.createQuery("SELECT e.id FROM Exam e WHERE e.id = :examId", Exam.class);
    examQuery.setParameter("examId", examId).getSingleResult();

    TypedQuery<Double> maxPointsQuery = entityManager.createQuery(
        "SELECT COALESCE(SUM(e.maxPoints), 0) FROM Exercise e WHERE e.exam.id = :examId",
        Double.class);
    return maxPointsQuery.setParameter("examId", examId).getSingleResult();
  }

  @Override
  public void changeState(long examId, ExamState newState) throws NotFoundException {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      entityManager.getTransaction().begin();
      Exam exam = entityManager.find(Exam.class, examId);
      if (exam == null) {
        NotFoundException ex = new NotFoundException("Exam with id " + examId + " does not exist.");
        LOGGER.error(ex.getMessage(), ex);
        throw ex;
      }
      exam.setState(newState);

      TypedQuery<ExerciseSolution> exerciseSolutionQuery = entityManager.createQuery(
          "SELECT es FROM ExerciseSolution es WHERE es.participation.exam.id = :examId",
          ExerciseSolution.class);
      List<ExerciseSolution> exerciseSolutionList =
          exerciseSolutionQuery.setParameter("examId", examId).getResultList();
      exerciseSolutionList.forEach(e -> e.setDone(false));

      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
      throw ex;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public boolean isFinished(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Long> unfinishedExerciseSolutionsQuery =
          entityManager.createQuery("SELECT COALESCE(COUNT(e), 0) FROM ExerciseSolution e "
              + "WHERE e.participation.exam.id = :examId AND e.isDone = false", Long.class);

      if (unfinishedExerciseSolutionsQuery.setParameter("examId", examId).getSingleResult() > 0) {
        return false;
      }
      return true;
    } finally {
      entityManager.close();
    }
  }

}
