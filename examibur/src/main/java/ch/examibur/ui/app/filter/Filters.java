package ch.examibur.ui.app.filter;

import spark.Request;
import spark.Response;

public class Filters {

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
   * Enable GZIP for all responses
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
