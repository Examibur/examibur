package ch.examibur.business.exam;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import java.util.List;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExamServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private final ExamService examService = INJECTOR.getInstance(ExamService.class);

  @Test
  public void testGetExamsForAuthor() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(4);
    Assert.assertEquals(4, exams.size());
  }

  @Test
  public void testGetExamsForAuthorWithNonexistentAuthorId() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(0);
    Assert.assertEquals(0, exams.size());
  }

  @Test
  public void testGetExam() throws ExamiburException {
    Exam exam = examService.getExam(4);
    Assert.assertNotNull(exam);
  }

  @Test(expected = NotFoundException.class)
  public void testGetExamWithNonexistentExamId() throws ExamiburException {
    examService.getExam(0);
    Assert.fail();
  }

}
