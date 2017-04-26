package ch.examibur.service.exception;

/**
 * Superclass of all exception that the examibur service throws.
 *
 */
public class ExamiburException extends Exception {

  private static final long serialVersionUID = 455471899913450401L;

  public ExamiburException(String message, Throwable cause) {
    super(message, cause);
  }

  public ExamiburException(String message) {
    super(message);
  }

}
