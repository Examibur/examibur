package ch.examibur.service.exception;

public class NotFoundException extends ExamiburException {

  private static final long serialVersionUID = -1476991523782828003L;

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(String message) {
    super(message);
  }

}
