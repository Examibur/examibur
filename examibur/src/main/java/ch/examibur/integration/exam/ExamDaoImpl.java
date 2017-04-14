package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;

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
      TypedQuery<Exam> examsForAuthorQuery = entityManager
          .createQuery("SELECT e FROM Exam e WHERE e.author.id = :authorId", Exam.class);
      return examsForAuthorQuery.setParameter("authorId", authorId).getResultList();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExamsForAuthor call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public Exam getExam(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<Exam> examQuery = entityManager
          .createQuery("SELECT e FROM Exam e WHERE e.id = :examId", Exam.class);
      return examQuery.setParameter("examId", examId).getSingleResult();
    } catch (Exception e) {
      LOGGER.error("Error occured during getExam call", e);
      throw e;
    } finally {
      entityManager.close();
    }
  }

}
