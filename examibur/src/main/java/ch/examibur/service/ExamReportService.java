package ch.examibur.service;

import ch.examibur.domain.Exam;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.report.ExerciseAverageMaxPointsComparison;
import ch.examibur.service.model.report.PassedParticipationComparison;
import java.util.List;

public interface ExamReportService {

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return the report object to compare successful and unsuccessful participations
   * @throws ExamiburException
   *           throws {@link NotFoundException} if the {@link Exam} or exam participations with the
   *           given id was/were not found or it was not possible to retrieve the total points of
   *           exam gradings for a specific participation. throws {@link AuthorizationException} if
   *           the user is not authorized to access this data. throws {@link CommunicationException}
   *           if an exception during the communication occurs.
   */
  public PassedParticipationComparison getPassedParticipationComparisonReport(long examId)
      throws ExamiburException;

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return the report object to compare max points and average points of each exercise
   * @throws ExamiburException
   *           throws {@link NotFoundException} if exercises with the given exam id were not found
   *           or it was not possible to retrieve the average points of the exercises for the given
   *           exam. throws {@link AuthorizationException} if the user is not authorized to access
   *           this data. throws {@link CommunicationException} if an exception during the
   *           communication occurs.
   */
  public List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(
      long examId) throws ExamiburException;

}
