package ch.examibur.service;

import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.model.report.PassedParticipationComparisonReport;

@FunctionalInterface
public interface ExamReportService {

  public PassedParticipationComparisonReport getPassedParticipationComparisonReport(long examId) throws ExamiburException;
  
}
