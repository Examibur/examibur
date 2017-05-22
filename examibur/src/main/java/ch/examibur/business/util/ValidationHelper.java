package ch.examibur.business.util;

import ch.examibur.integration.exercisesolution.BrowseSolutionsMode;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.exception.ValidationException;
import ch.examibur.service.model.ApprovalResult;
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
      InvalidParameterException invalidParameterException =
          new InvalidParameterException("id parameter is negative");
      logger.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }
  }

  /**
   * Checks if {@link BrowseSolutionsMode} is null.
   *
   * @param mode
   *          the {@link BrowseSolutionsMode} to check.
   * @param logger
   *          the logger to log the exception.
   * @throws InvalidParameterException
   *           if the given mode is null.
   */
  public static void checkForNullValue(BrowseSolutionsMode mode, Logger logger)
      throws InvalidParameterException {
    if (mode == null) {
      InvalidParameterException invalidParameterException =
          new InvalidParameterException("BrowseSolutionsMode parameter is null");
      logger.error(invalidParameterException.getMessage(), invalidParameterException);
      throw invalidParameterException;
    }
  }

  /**
   * @param rawResult
   *          the unvalidated string for an {@link ApprovalResult}.
   * @param logger
   *          the logger to log the exception.
   * @return a validated {@link ApprovalResult}.
   * @throws ValidationException
   *           if the rawResult parameter is not a valid {@link ApprovalResult}.
   */
  public static ApprovalResult getValidatedApprovalResult(String rawResult, Logger logger)
      throws ValidationException {
    ApprovalResult approvalResult = ApprovalResult.fromString(rawResult);
    if (approvalResult == null) {
      ValidationException validationException =
          new ValidationException("String [" + rawResult + "] is not a valid ApprovalResult");
      logger.error(validationException.getMessage(), validationException);
      throw validationException;
    }
    return approvalResult;
  }
}
