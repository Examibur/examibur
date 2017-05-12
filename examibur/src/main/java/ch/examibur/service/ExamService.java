package ch.examibur.service;

import ch.examibur.domain.Exam;
import ch.examibur.domain.ExamState;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
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
  List<Exam> getExamsForAuthor(long authorId) throws ExamiburException;

  /**
   * Gets the exams for the given author in the given state.
   *
   * @param authorId
   *          the user id to get the exams for.
   * @param state
   *          filter for exams of the given state.
   * @return a list of exams for which the user is the author. If the user doesn't exist, an empty
   *         list is returned.
   * @throws ExamiburException
   *           throws an {@link CommunicationException} if an exception during the communication
   *           occurs. throws {@link InvalidParameterException} if authorId < 0.
   */
  List<Exam> getExamsForAuthor(long authorId, ExamState state) throws ExamiburException;

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
  Exam getExam(long examId) throws ExamiburException;

  /**
   * @param examId
   *          the id of the exam.
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if examid < 0. throws
   *           {@link NotFoundException} if the {@link Exam} with the given id doesn't exist. throws
   *           {@link AuthorizationException} if the user is not authorized. throws
   *           {@link CommunicationException} if an exception during the communication occurs.
   */
  double getMaxPoints(long examId) throws ExamiburException;

}
