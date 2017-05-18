package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class ExamDaoImpl implements ExamDao {

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

}
