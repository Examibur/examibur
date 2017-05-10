package ch.examibur.business.service;

import ch.examibur.domain.Exercise;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.service.ExerciseService;
import com.google.inject.Inject;
import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

  private final ExerciseDao exerciseDao;

  @Inject
  public ExerciseServiceImpl(ExerciseDao exerciseDao) {
    this.exerciseDao = exerciseDao;
  }

  @Override
  public List<Exercise> getExercises(long examId) {
    return exerciseDao.getExercises(examId);
  }

}
