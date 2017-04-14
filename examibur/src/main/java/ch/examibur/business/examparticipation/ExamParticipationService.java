package ch.examibur.business.examparticipation;

import ch.examibur.ui.app.model.ExamParticipantOverview;
import java.util.List;

public interface ExamParticipationService {

  List<ExamParticipantOverview> getExamParticipantsOverview(long examId);

}
