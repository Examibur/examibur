package ch.examibur.service.exception;

public class IllegalOperationException extends ExamiburException {

  private static final long serialVersionUID = 4314742452559974822L;

  public IllegalOperationException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalOperationException(String message) {
    super(message);
  }

}
