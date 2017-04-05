package ch.examibur.ui.app.filter;

import spark.Request;
import spark.Response;

public class Filters {
  
  private Filters() {
  }

  private Filters() {

  }

  /**
   * If a user manually manipulates paths and forgets to add a trailing slash, redirect the user to
   * the correct path.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   */
  public static void addTrailingSlashes(Request request, Response response) {
    if (!request.pathInfo().endsWith("/")) {
      response.redirect(request.pathInfo() + "/");
    }
  }

  /**
   * Handles the user authentication and creates session cookie.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   */
  public static void handleAuthentication(Request request, Response response) {
    // TODO implement authentication
    request.session().attribute("user", "4");
  }

  /**
   * Enable GZIP for all responses.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The current response.
   */
  public static void addGzipHeader(Request request, Response response) {
    response.header("Content-Encoding", "gzip");
  }

}
