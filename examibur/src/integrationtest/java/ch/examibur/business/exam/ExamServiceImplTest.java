package ch.examibur.business.exam;

import ch.examibur.business.DatabaseResource;
import ch.examibur.domain.Exam;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExamServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private final ExamService examService = new ExamServiceImpl();

  @Test
  public void testGetExamsForAuthor() {
    List<Exam> exams = examService.getExamsForAuthor(4);
    Assert.assertEquals(4, exams.size());
  }

  @Test
  public void testGetExamsForAuthorWithNonexistentAuthorId() {
    List<Exam> exams = examService.getExamsForAuthor(0);
    Assert.assertEquals(0, exams.size());
  }

  @Test
  public void testGetExam() {
    Exam exam = examService.getExam(4);
    Assert.assertNotNull(exam);
  }

  @Test(expected = NoResultException.class)
  public void testGetExamWithNonexistentExamId() {
    examService.getExam(0);
    Assert.fail();
  }

}
