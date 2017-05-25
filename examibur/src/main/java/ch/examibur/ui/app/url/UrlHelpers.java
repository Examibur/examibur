package ch.examibur.ui.app.url;

import ch.examibur.service.exception.InvalidParameterException;
import spark.Request;

public final class UrlHelpers {
  private UrlHelpers() {
  }

  /**
   * Parses the long value of the given parameter. The returned long value must be > 0.
   * 
   * @throws InvalidParameterException
   *           if the given url parameter is not set or < 0.
   */
  public static long getUnsignedLongUrlParameter(Request request, UrlParameter urlParameter)
      throws InvalidParameterException {
    String value = request.params(urlParameter.toString());
    if (value == null) {
      throw new InvalidParameterException("The given url parameter value is missing");
    }
    long parsed = Long.parseLong(value);
    if (parsed < 0) {
      throw new InvalidParameterException("The given url parameter value is < 0");
    }
    return parsed;
  }

  /**
   * Parses the double value of the given body parameter. The returned double value must be > 0.
   * 
   * @throws InvalidParameterException
   *           if the given url parameter is not set or < 0.
   */
  public static double getUnsignedDoubleBodyParameter(Request request, String bodyParameter)
      throws InvalidParameterException {
    String value = request.queryParams(bodyParameter);
    if (value == null) {
      throw new InvalidParameterException("The given body parameter value is missing");
    }
    double parsed = Double.parseDouble(value);
    if (parsed < 0) {
      throw new InvalidParameterException("The given body parameter value is < 0");
    }
    return parsed;
  }
}
