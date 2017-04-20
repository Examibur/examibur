package ch.examibur.business.exam;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExamServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private final ExamService examService = INJECTOR.getInstance(ExamService.class);

  @Test
  public void testGetExamsForAuthor() throws AuthorizationException, IOException {
    List<Exam> exams = examService.getExamsForAuthor(4);
    Assert.assertEquals(4, exams.size());
  }

  @Test
  public void testGetExamsForAuthorWithUserWithoutExams()
      throws AuthorizationException, IOException {
    List<Exam> exams = examService.getExamsForAuthor(1L);
    Assert.assertTrue(exams.isEmpty());
  }

  @Test
  public void testGetExamsForAuthorWithNonexistentAuthorId()
      throws AuthorizationException, IOException {
    List<Exam> exams = examService.getExamsForAuthor(0L);
    Assert.assertTrue(exams.isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetExamsForNegativeAuthorId() throws AuthorizationException, IOException {
    examService.getExamsForAuthor(-1L);
    Assert.fail();
  }

  @Test
  public void testGetExam() throws NotFoundException, AuthorizationException, IOException {
    Exam exam = examService.getExam(4L);
    Assert.assertNotNull(exam);
    Assert.assertNotNull(exam.getAllowedUtilities());
  }

  @Test(expected = NotFoundException.class)
  public void testGetExamWithNonexistentExamId()
      throws NotFoundException, AuthorizationException, IOException {
    examService.getExam(0L);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetExamWithNegativeExamId()
      throws NotFoundException, AuthorizationException, IOException {
    examService.getExam(-1L);
    Assert.fail();
  }

}
