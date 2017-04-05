package ch.examibur.business.exercise;

import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercise.ExerciseDaoImpl;

public class ExerciseServiceImpl implements ExerciseService {
  
  private final ExerciseDao exerciseDao;
  
  public ExerciseServiceImpl() {
    exerciseDao = new ExerciseDaoImpl();
  }

  @Override
  public double getMaxPoints(long examId) {
    return exerciseDao.getMaxPoints(examId);
  }

}
