package ch.examibur.ui.app.routing;

import spark.Request;

public final class RoutingHelpers {
  private RoutingHelpers() {
  }

  /**
   * Parses the long value of the given parameter. The returned long value must be > 0. Otherwise,
   * an {@link IllegalArgumentException} is thrwon. If the given url parameter is not set, a
   * {@link IllegalArgumentException} is thrown.
   */
  public static long getUnsignedLongUrlParameter(Request request, UrlParameter urlParameter) {
    String value = request.params(urlParameter.toString());
    if (value == null) {
      throw new IllegalArgumentException("The given url parameter value is missing");
    }
    long parsed = Long.parseLong(value);
    if (parsed < 0) {
      throw new IllegalArgumentException("The given url parameter value is < 0");
    }
    return parsed;
  }
}
