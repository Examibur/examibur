package ch.examibur.business.exercisegrading;

import ch.examibur.domain.ExerciseGrading;

public interface ExerciseGradingService {
 ExerciseGrading getGradingForExerciseSolution(long exerciseSolutionId);
 ExerciseGrading getReviewForExerciseSolution(long exerciseSolutionId);
}
