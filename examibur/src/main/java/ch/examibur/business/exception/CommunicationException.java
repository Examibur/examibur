package ch.examibur.business.exception;

public class CommunicationException extends ExamiburException {

  private static final long serialVersionUID = -6817522366563427598L;

  public CommunicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public CommunicationException(String message) {
    super(message);
  }

}
