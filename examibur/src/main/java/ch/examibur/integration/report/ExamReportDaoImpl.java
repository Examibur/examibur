package ch.examibur.integration.report;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.domain.Exercise;
import ch.examibur.domain.TextExercise;
import ch.examibur.domain.aggregation.ExamPerformance;
import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.util.grading.GradingUtil;
import ch.examibur.integration.util.grading.strategy.BaseGradingStrategy;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class ExamReportDaoImpl implements ExamReportDao {

  private static final double MIN_GRADING = 3.75;

  private final Provider<EntityManager> entityManagerProvider;
  private final ExamDao examDao;
  private final ExamParticipationDao examParticipationDao;
  private final ExerciseDao exerciseDao;
  private final ExerciseGradingDao exerciseGradingDao;

  /**
   * Constructor.
   * 
   * @param entityManagerProvider
   *          the entity manager provider to create db transactions
   * @param examDao
   *          the data access object to access exams
   * @param examParticipationDao
   *          the data access object to access exam participations
   * @param exerciseDao
   *          the data access object to access exercises
   * @param exerciseGradingDao
   *          the data access object to access exercise gradings
   */
  @Inject
  public ExamReportDaoImpl(Provider<EntityManager> entityManagerProvider, ExamDao examDao,
      ExamParticipationDao examParticipationDao, ExerciseDao exerciseDao,
      ExerciseGradingDao exerciseGradingDao) {
    this.entityManagerProvider = entityManagerProvider;
    this.examDao = examDao;
    this.examParticipationDao = examParticipationDao;
    this.exerciseDao = exerciseDao;
    this.exerciseGradingDao = exerciseGradingDao;
  }

  @Override
  public PassedParticipationComparison getPassedParticipationComparisonReport(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      double maxPoints = examDao.getMaxPoints(examId, entityManager);
      int passedParticipations = 0;

      List<ExamParticipation> examParticipations = examParticipationDao
          .getExamParticipations(examId, entityManager);
      for (ExamParticipation examParticipation : examParticipations) {
        double totalPoints = exerciseGradingDao
            .getTotalPointsOfExamGradings(examParticipation.getId(), entityManager);

        double grading = GradingUtil.calculateGrading(new BaseGradingStrategy(), totalPoints,
            maxPoints);

        if (grading >= MIN_GRADING) {
          passedParticipations++;
        }
      }
      return new PassedParticipationComparison(passedParticipations, examParticipations.size());
    } finally {
      entityManager.close();
    }
  }

  @Override
  public List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      List<ExerciseAverageMaxPointsComparison> exerciseAverageMaxPointsComparisonList = new ArrayList<>();
      for (Exercise exercise : exerciseDao.getExercises(examId, entityManager)) {
        // TODO remove this ugly cast as soon as issue 99 is resolved
        String title = ((TextExercise) exercise).getTitle();
        double averagePoints = exerciseGradingDao.getAveragePointsOfExercise(exercise.getId(),
            entityManager);
        exerciseAverageMaxPointsComparisonList.add(
            new ExerciseAverageMaxPointsComparison(title, exercise.getMaxPoints(), averagePoints));
      }
      return exerciseAverageMaxPointsComparisonList;
    } finally {
      entityManager.close();
    }
  }

  @Override
  public ExamPerformance getExamPerformanceReport(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      double maxPoints = examDao.getMaxPoints(examId, entityManager);

      List<Double> gradings = new ArrayList<>();

      List<ExamParticipation> examParticipations = examParticipationDao
          .getExamParticipations(examId, entityManager);
      for (ExamParticipation examParticipation : examParticipations) {
        double totalPoints = exerciseGradingDao
            .getTotalPointsOfExamGradings(examParticipation.getId(), entityManager);

        double grading = GradingUtil.calculateGrading(new BaseGradingStrategy(), totalPoints,
            maxPoints);
        gradings.add(grading);
      }
      double averageGrade = GradingUtil.calculateAverageGrade(gradings);
      double medianGrade = GradingUtil.calculateMedianGrade(gradings);
      return new ExamPerformance(averageGrade, medianGrade);
    } finally {
      entityManager.close();
    }
  }
  
  @Override
  public boolean isReportRetrievalPossible(long examId) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      List<Exercise> exercises = exerciseDao.getExercises(examId, entityManager);
      if (exercises.isEmpty()) {
        return false;
      }
      List<ExamParticipation> examParticipations = examParticipationDao
          .getExamParticipations(examId, entityManager);
      if (examParticipations.isEmpty()) {
        return false;
      }
      return true;
    } finally {
      entityManager.close();
    }
  }
}
