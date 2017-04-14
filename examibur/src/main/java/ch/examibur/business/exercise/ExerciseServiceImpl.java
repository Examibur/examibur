package ch.examibur.business.exercise;

import ch.examibur.domain.Exercise;
import ch.examibur.integration.exercise.ExerciseDao;

import com.google.inject.Inject;
import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

  private final ExerciseDao exerciseDao;

  @Inject
  public ExerciseServiceImpl(ExerciseDao exerciseDao) {
    this.exerciseDao = exerciseDao;
  }

  @Override
  public double getMaxPoints(long examId) {
    return exerciseDao.getMaxPoints(examId);
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    return exerciseDao.getExercises(examId);
  }

}
