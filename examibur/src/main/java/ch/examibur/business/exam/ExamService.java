package ch.examibur.business.exam;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.CommunicationException;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.InvalidParameterException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import java.util.List;

public interface ExamService {

  /**
   * @param authorId
   *          the user id to get the exams for.
   * @return a list of exams for which the user is the author. If the user doesn't exist, an empty
   *         list is returned.
   * @throws ExamiburException
   *           throws an {@link CommunicationException} if an exception during the communication
   *           occurs. throws {@link InvalidParameterException} if authorId < 0.
   */
  public List<Exam> getExamsForAuthor(long authorId) throws ExamiburException;

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return the {@link Exam} with the given id.
   * @throws ExamiburException
   *           throws {@link NotFoundException} if the {@link Exam} with the given id was not found.
   *           throws {@link AuthorizationException} if the user is not authorized to display this
   *           exam. throws {@link CommunicationException} if an exception during the communication
   *           occurs.
   */
  public Exam getExam(long examId) throws ExamiburException;

}
