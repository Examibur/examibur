package ch.examibur.service.exception;

public class ValidationException extends ExamiburException {

  private static final long serialVersionUID = 6275984477188893803L;

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(String message) {
    super(message);
  }
}
