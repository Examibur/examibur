package ch.examibur.integration.utils;

public class InitializationException extends RuntimeException {

  private static final long serialVersionUID = -654870793059394861L;

  public InitializationException(String message) {
    super(message);
  }

  public InitializationException(Throwable cause) {
    super(cause);
  }

  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }

}
