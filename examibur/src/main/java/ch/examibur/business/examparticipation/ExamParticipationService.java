package ch.examibur.business.examparticipation;

import ch.examibur.business.model.ExamParticipantOverview;
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
