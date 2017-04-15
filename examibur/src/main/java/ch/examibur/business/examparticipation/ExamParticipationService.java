package ch.examibur.business.examparticipation;

import ch.examibur.ui.app.model.ExamParticipantOverview;
import java.util.List;

@FunctionalInterface
public interface ExamParticipationService {

  List<ExamParticipantOverview> getExamParticipantsOverview(long examId);

}
