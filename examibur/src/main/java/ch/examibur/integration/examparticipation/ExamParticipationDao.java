package ch.examibur.integration.examparticipation;

import ch.examibur.domain.ExamParticipation;
import java.util.List;

public interface ExamParticipationDao {
  
  public List<ExamParticipation> getExamParticipations(long examId);
  
}
