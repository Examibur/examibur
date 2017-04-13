package ch.examibur.integration;

public class SingleResultNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3716636313603746393L;

  public SingleResultNotFoundException(String message) {
    super(message);
  }
  
  public SingleResultNotFoundException(String message, Throwable t) {
    super(message, t);
  }
  
}
