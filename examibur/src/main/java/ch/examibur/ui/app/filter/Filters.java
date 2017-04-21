package ch.examibur.ui.app.filter;

import ch.examibur.ui.app.util.BreadCrumbEntry;
import ch.examibur.ui.app.util.RequestAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import spark.Request;
import spark.Response;

public class Filters {

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
   * Populates the model with all globally required/available fields to simplify rendering.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   */
  public static void addBaseModel(Request request, Response response) {
    Map<String, Object> baseModel = new HashMap<>();
    baseModel.put(RequestAttributes.USER, "Max Muster");
    baseModel.put(RequestAttributes.TITLE, "Examibur");
    baseModel.put(RequestAttributes.URL, request.uri());
    baseModel.put(RequestAttributes.BREADCRUMB, new LinkedList<BreadCrumbEntry>());
    request.attribute(RequestAttributes.MODEL, baseModel);
  }

  /**
   * Handles the user authentication.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   */
  public static void handleAuthentication(Request request, Response response) {
    // TODO implement authentication
    request.attribute("user", 4L);
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
