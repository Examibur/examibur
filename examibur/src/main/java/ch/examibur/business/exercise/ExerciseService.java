package ch.examibur.business.exercise;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.CommunicationException;
import ch.examibur.business.exception.ExamiburException;
import ch.examibur.business.exception.InvalidParameterException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;

public interface ExerciseService {

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
  public double getMaxPoints(long examId) throws ExamiburException;

}
