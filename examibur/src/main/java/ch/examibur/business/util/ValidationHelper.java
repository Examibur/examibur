package ch.examibur.business.util;

import ch.examibur.business.exception.InvalidParameterException;
import org.slf4j.Logger;

public final class ValidationHelper {

  private ValidationHelper() {
  }

  /**
   * Checks if id is negative.
   * 
   * @param id
   *          the id to check.
   * @param logger
   *          the logger to log the exception.
   * @throws InvalidParameterException
   *           if the given id is negative.
   */
  public static void checkForNegativeId(long id, Logger logger) throws InvalidParameterException {
    if (id < 0) {
      InvalidParameterException invalidParameterException = new InvalidParameterException(
          "id parameter is negative");
      logger.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }
  }
}
