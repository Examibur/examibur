package ch.examibur.service;

import ch.examibur.domain.ExamParticipation;
import ch.examibur.service.exception.AuthorizationException;
import ch.examibur.service.exception.CommunicationException;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.NotFoundException;
import ch.examibur.service.model.ExamParticipantOverview;
import java.util.List;

public interface ExamParticipationService {

  /**
   * @param examId
   *          id of the exam. This exam must exist!
   * @return a list of all participants of the given exam.
   */
  List<ExamParticipantOverview> getExamParticipantsOverview(long examId);

  /**
   * @param examParticipationId
   *          the id of the {@link ExamParticipation}.
   * @return the {@link ExamParticipation} with the given id.
   * @throws ExamiburException
   *           throws {@link InvalidParameterException} if examParticipationId < 0. throws
   *           {@link NotFoundException} if the {@link ExamParticipation} with the given id was not
   *           found. throws {@link AuthorizationException} if the user is not authorized to display
   *           this ExamParticipation. throws {@link CommunicationException} if an exception during
   *           the communication occurs.
   */
  ExamParticipation getExamParticipation(long examParticipationId) throws ExamiburException;
}
