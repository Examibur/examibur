package ch.examibur.service;

import ch.examibur.domain.Exam;
import ch.examibur.domain.Exercise;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import java.util.List;

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

  /**
   * @param examId
   *          the id of the exam. This exam must exist!
   * @return a list of all exercises for the given exam.
   */
  public List<Exercise> getExercises(long examId);

}
