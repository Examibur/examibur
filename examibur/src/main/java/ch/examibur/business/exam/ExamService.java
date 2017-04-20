package ch.examibur.business.exam;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import java.io.IOException;
import java.util.List;

public interface ExamService {

  /**
   * @param authorId
   *          the user id to get the exams for.
   * @return a list of exams for which the user is the author. If the user doesn't exist, an empty
   *         list is returned.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  public List<Exam> getExamsForAuthor(long authorId) throws AuthorizationException, IOException;

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return the {@link Exam} with the given id.
   * @throws NotFoundException
   *           if the {@link Exam} with the given id was not found.
   * @throws AuthorizationException
   *           if the user is not authorized to display this exam.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  public Exam getExam(long examId) throws NotFoundException, AuthorizationException, IOException;

}
