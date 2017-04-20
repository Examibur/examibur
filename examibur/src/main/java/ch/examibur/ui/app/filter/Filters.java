package ch.examibur.ui.app.filter;

import ch.examibur.ui.app.util.BreadCrumbEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import spark.Request;
import spark.Response;

public class Filters {

  public static final String MODEL = "model";
  public static final String USER = "user";
  public static final String URL = "url";
  public static final String TITLE = "title";
  public static final String BREADCRUMB = "breadcrumb";

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
    baseModel.put(USER, "Max Muster");
    baseModel.put(TITLE, "Examibur");
    baseModel.put(URL, request.uri());
    baseModel.put(BREADCRUMB, new LinkedList<BreadCrumbEntry>());
    request.attribute(MODEL, baseModel);
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
    request.attribute("user", 4l);
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
