package ch.examibur.service;

import ch.examibur.service.model.ExamParticipantOverview;
import java.util.List;

@FunctionalInterface
public interface ExamParticipationService {

  /**
   * @param examId
   *          id of the exam. This exam must exist!
   * @return a list of all participants of the given exam.
   */
  List<ExamParticipantOverview> getExamParticipantsOverview(long examId);

}
