package ch.examibur.integration.exercise;

import ch.examibur.domain.Exercise;
import java.util.List;
import javax.persistence.EntityManager;

public interface ExerciseDao {

  /**
   * @param examId
   *          the id of exam
   * @return all exercises for the specified exam.
   */
  List<Exercise> getExercises(long examId);

  /**
   * @see ch.examibur.integration.exercise.ExerciseDao#getExercises(long)
   * @param entityManager
   *          this method is used in another dao and a global transaction will be used
   */
  List<Exercise> getExercises(long examId, EntityManager entityManager);

  /**
   * @param exerciseId
   *          the id of the exercise
   * @return the exercise for the specified exercise id.
   */
  Exercise getExercise(long exerciseId);

}
