package ch.examibur.business.exercise;

import ch.examibur.integration.exercise.ExerciseDao;

import com.google.inject.Inject;

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

}
