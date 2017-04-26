package ch.examibur.business.exam;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

public class ExamServiceImplTest {

  @ClassRule
  public static final DatabaseResource RES = new DatabaseResource();

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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
  }

  @Test
  public void testGetExam()
      throws NotFoundException, AuthorizationException, IOException, ParseException {
    Exam exam = examService.getExam(4L);

    Assert.assertNotNull(exam.getAuthor());
    Assert.assertNotNull(exam.getModule());
    Assert.assertNotNull(exam.getAllowedUtilities());

    Assert.assertNotNull(exam);
    Assert.assertEquals(4L, exam.getId());
    Assert.assertEquals(60, exam.getAllowedTimeInMin());
    Assert.assertEquals(new Date(DATE_FORMAT.parse("2017-01-16").getTime()), exam.getDueDate());
    Assert.assertEquals(ExamState.APPROVAL, exam.getState());
    Assert.assertEquals(9, exam.getAuthor().getId());
    Assert.assertEquals(6, exam.getModule().getId());
    Assert.assertEquals(new Date(DATE_FORMAT.parse("2017-01-20").getTime()),
        exam.getLastModified());
    Assert.assertEquals(new Date(DATE_FORMAT.parse("2017-01-03").getTime()),
        exam.getCreationDate());

  }

  @Test(expected = NotFoundException.class)
  public void testGetExamWithNonexistentExamId()
      throws NotFoundException, AuthorizationException, IOException {
    examService.getExam(0L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetExamWithNegativeExamId()
      throws NotFoundException, AuthorizationException, IOException {
    examService.getExam(-1L);
  }

}
