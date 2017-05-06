package ch.examibur.service;

import ch.examibur.domain.Exam;
import ch.examibur.domain.aggregation.ExamPerformance;
import ch.examibur.domain.aggregation.ExerciseAverageMaxPointsComparison;
import ch.examibur.domain.aggregation.PassedParticipationComparison;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.NotFoundException;
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
  PassedParticipationComparison getPassedParticipationComparisonReport(long examId)
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
  List<ExerciseAverageMaxPointsComparison> getExerciseAverageMaxPointsComparisonReport(long examId)
      throws ExamiburException;

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return the report object to analyze the average grading and the median
   * @throws ExamiburException
   *           throws {@link NotFoundException} if the {@link Exam} or exam participations with the
   *           given id was/were not found or it was not possible to retrieve the total points of
   *           exam gradings for a specific participation. throws {@link AuthorizationException} if
   *           the user is not authorized to access this data. throws {@link CommunicationException}
   *           if an exception during the communication occurs.
   */
  ExamPerformance getExamPerformanceReport(long examId) throws ExamiburException;

  /**
   * @param examId
   *          the id of the {@link Exam}.
   * @return true, if it's possible to generate all of the reports with the current data
   * @throws ExamiburException
   *           throws {@link NotFoundException} if exam participations or exercises with the given
   *           id were not found. throws {@link AuthorizationException} if the user is not
   *           authorized to access this data. throws {@link CommunicationException} if an exception
   *           during the communication occurs.
   */
  boolean isReportRetrievalPossible(long examId) throws ExamiburException;

}
