package ch.examibur.service.exception;

public class InvalidParameterException extends ExamiburException {

  private static final long serialVersionUID = -2637694253103142214L;

  public InvalidParameterException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidParameterException(String message) {
    super(message);
  }

}
