package ch.examibur.service.exception;

public class AuthorizationException extends ExamiburException {

  private static final long serialVersionUID = 4135500762348965806L;

  public AuthorizationException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthorizationException(String message) {
    super(message);
  }

}
