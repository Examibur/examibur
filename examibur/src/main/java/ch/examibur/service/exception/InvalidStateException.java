package ch.examibur.service.exception;

public class InvalidStateException extends ExamiburException {

  private static final long serialVersionUID = 8197046906772744360L;

  public InvalidStateException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidStateException(String message) {
    super(message);
  }

}
