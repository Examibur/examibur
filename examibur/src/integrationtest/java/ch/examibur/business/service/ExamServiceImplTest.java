package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.service.ExamService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ExamServiceImplTest {

  @Rule
  public final DatabaseResource res = new DatabaseResource();

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private final ExamService examService =
      IntegrationTestUtil.getInjector().getInstance(ExamService.class);

  @Test
  public void testGetExamsForAuthor() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(4);
    Assert.assertEquals(4, exams.size());
  }

  @Test
  public void testGetExamsForAuthorWithUserWithoutExams() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(1L);
    Assert.assertTrue(exams.isEmpty());
  }

  @Test
  public void testGetExamsForAuthorWithNonexistentAuthorId() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(0L);
    Assert.assertTrue(exams.isEmpty());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExamsForNegativeAuthorId() throws ExamiburException {
    examService.getExamsForAuthor(-1L);
  }

  @Test
  public void testGetExamsForAuthorInState() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(4, ExamState.CORRECTION);
    Assert.assertEquals(1, exams.size());
  }

  @Test
  public void testGetExamsForAuthorInStateWithoutExams() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(1, ExamState.CORRECTION);
    Assert.assertEquals(0, exams.size());
  }

  @Test
  public void testGetExamsForAuthorInStateWithNonexistentAuthorId() throws ExamiburException {
    List<Exam> exams = examService.getExamsForAuthor(0L, ExamState.CORRECTION);
    Assert.assertTrue(exams.isEmpty());
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExamsForNegativeAuthorIdInState() throws ExamiburException {
    examService.getExamsForAuthor(-1L, ExamState.CORRECTION);
  }

  @Test
  public void testGetExam() throws ExamiburException, ParseException {
    Exam exam = examService.getExam(4L);

    Assert.assertNotNull(exam.getAuthor());
    Assert.assertNotNull(exam.getModule());
    Assert.assertNotNull(exam.getAllowedUtilities());

    Assert.assertNotNull(exam);
    Assert.assertEquals(4L, exam.getId());
    Assert.assertEquals(60, exam.getAllowedTimeInMin());
    Assert.assertEquals(new Date(dateFormat.parse("2017-01-16").getTime()), exam.getDueDate());
    Assert.assertEquals(ExamState.APPROVAL, exam.getState());
    Assert.assertEquals(9, exam.getAuthor().getId());
    Assert.assertEquals(6, exam.getModule().getId());
    Assert.assertEquals(new Date(dateFormat.parse("2017-01-20").getTime()), exam.getLastModified());
    Assert.assertEquals(new Date(dateFormat.parse("2017-01-03").getTime()), exam.getCreationDate());

  }

  @Test(expected = NotFoundException.class)
  public void testGetExamWithNonexistentExamId() throws ExamiburException {
    examService.getExam(0L);
  }

  @Test(expected = InvalidParameterException.class)
  public void testGetExamWithNegativeExamId() throws ExamiburException {
    examService.getExam(-1L);
  }

  @Test
  public void testGetMaxPoints() throws ExamiburException {
    double maxPoints = examService.getMaxPoints(4);
    Assert.assertEquals(6, maxPoints, 0.01);
  }

  @Test
  public void testGetMaxPointsForExamWithoutExercises() throws ExamiburException {
    double maxPoints = examService.getMaxPoints(1);
    Assert.assertEquals(0, maxPoints, 0.01);
  }

  @Test(expected = NotFoundException.class)
  public void testGetMaxPointsWithNonexistentExamId() throws ExamiburException {
    examService.getMaxPoints(0);
  }

  @Test
  public void testTransitionToReview() throws ExamiburException {
    long examId = 7L;
    examService.setNextState(examId);
    Exam exam = examService.getExam(examId);
    Assert.assertEquals(ExamState.REVIEW, exam.getState());
  }

  @Test(expected = IllegalOperationException.class)
  public void testTransitionWithUnfinishedExerciseSolutions() throws ExamiburException {
    examService.setNextState(6L);
  }

  @Test(expected = IllegalOperationException.class)
  public void testTransitionOverLastState() throws ExamiburException {
    examService.setNextState(1L);
  }

  @Test(expected = NotFoundException.class)
  public void testTransitionWithNonexistentExamId() throws ExamiburException {
    examService.setNextState(0L);
  }

}
