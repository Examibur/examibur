package ch.examibur.business.exercise;

import ch.examibur.business.exception.AuthorizationException;
import ch.examibur.business.exception.NotFoundException;
import ch.examibur.domain.Exam;
import ch.examibur.domain.Exercise;

import java.util.List;
import java.io.IOException;

public interface ExerciseService {

  /**
   * @param examId
   *          the id of the exam. if the id is < 0, an IllegalArgumentException is thrown.
   * @return the total sum of maxPoints for all exercises in the {@link Exam} with the given id.
   * @throws NotFoundException
   *           if the {@link Exam} with the given id doesn't exist
   * @throws AuthorizationException
   *           if the user is not authorized.
   * @throws IOException
   *           if an exception during the communication occurs
   */
  public double getMaxPoints(long examId)
      throws NotFoundException, AuthorizationException, IOException;

  public List<Exercise> getExercises(long examId); 

}
