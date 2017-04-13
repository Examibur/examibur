package ch.examibur.business.exercise;

import ch.examibur.business.exam.ExamServiceImpl;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercise.ExerciseDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExerciseServiceImpl implements ExerciseService {
  
  private final ExerciseDao exerciseDao;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

  
  public ExerciseServiceImpl() {
    exerciseDao = new ExerciseDaoImpl();
  }

  @Override
  public double getMaxPoints(long examId) {
    return exerciseDao.getMaxPoints(examId);
  }

}
