package ch.examibur.integration.exam;

import ch.examibur.domain.Exam;
import ch.examibur.integration.utils.EntityManagerHelper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public final class ExamDaoImpl implements ExamDao {

  @Override
  public List<Exam> getExamsForAuthor(long authorId) {
    EntityManager entityManager = EntityManagerHelper.INSTANCE.createEntityManager();
    try {
      TypedQuery<Exam> examsForAuthorQuery = entityManager
          .createQuery("SELECT e FROM Exam e WHERE e.author.id = :authorId", Exam.class);
      return examsForAuthorQuery.setParameter("authorId", authorId).getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return new ArrayList<Exam>();
  }

}
