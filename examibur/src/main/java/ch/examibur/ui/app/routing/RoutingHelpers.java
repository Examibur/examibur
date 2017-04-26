package ch.examibur.ui.app.routing;

import ch.examibur.service.exception.InvalidParameterException;
import spark.Request;

public final class RoutingHelpers {
  private RoutingHelpers() {
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
}
