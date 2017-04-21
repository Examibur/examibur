package ch.examibur.business.util;

import org.slf4j.Logger;

public final class ValidationHelper {

  private ValidationHelper() {
  }

  /**
   * Checks if id is negative. If it is, throw an {@link IllegalArgumentException}
   * 
   * @param id
   *          the id to check.
   * @param logger
   *          the logger to log the exception.
   */
  public static void checkForNegativeId(long id, Logger logger) {
    if (id < 0) {
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
          "id parameter is negative");
      logger.error(illegalArgumentException.getMessage(), illegalArgumentException);
      throw illegalArgumentException;
    }
  }
}
