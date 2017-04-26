package ch.examibur.business.service;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.service.ExamParticipationService;
import ch.examibur.service.model.ExamParticipantOverview;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ExamParticipationServiceImpl implements ExamParticipationService {

  private final ExamParticipationDao examParticipationDao;
  private final ExerciseGradingDao exerciseGradingDao;
  private final ExerciseDao exerciseDao;

  /**
   * Constructor.
   * 
   * @param examParticipation
   *          the data access object to access exam participations
   * @param exerciseGradingDao
   *          the data access object to access exercise gradings
   * @param exerciseDao
   *          the data access object to access exercises
   */
  @Inject
  public ExamParticipationServiceImpl(ExamParticipationDao examParticipation, 
      ExerciseGradingDao exerciseGradingDao, ExerciseDao exerciseDao) {
    this.examParticipationDao = examParticipation;
    this.exerciseGradingDao = exerciseGradingDao;
    this.exerciseDao = exerciseDao;
  }
  
  @Override
  public List<ExamParticipantOverview> getExamParticipantsOverview(long examId) {
    List<ExamParticipation> examParticipations = examParticipationDao.getExamParticipations(examId);
    
    List<ExamParticipantOverview> examParticipantsOverwiew = new ArrayList<>();
    for (ExamParticipation examParticipation : examParticipations) {
      ExamParticipantOverview examParticipantOverview = new ExamParticipantOverview();
      
      examParticipantOverview.setExamParticipation(examParticipation);
      
      long examParticipationId = examParticipation.getId();
      double totalPoints = exerciseGradingDao.getTotalPointsOfExamGradings(examParticipationId);
      examParticipantOverview.setTotalPoints(totalPoints);
      
      double maxPoints = exerciseDao.getMaxPoints(examId);
      examParticipantOverview.setGrading(calculateGrading(totalPoints, maxPoints));
      
      double progress = exerciseGradingDao.getProgressOfExamGradings(examParticipationId);
      examParticipantOverview.setProgress(progress);
      
      examParticipantsOverwiew.add(examParticipantOverview);
    }
    
    return examParticipantsOverwiew;
  }
  
  private double calculateGrading(double totalPoints, double maxPoints) {
    double grading = (totalPoints / maxPoints) * 5 + 1;
    return Math.round(grading * 100) / 100d;
  }
  
}
