package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.exception;
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.http.HttpStatus;

import spark.Request;
import spark.Response;

public class ExceptionController extends Controller {

  public ExceptionController(Controller preController) {
    super(preController, "*");
  }

  /**
   * Handles Exception thrown in a controller.
   * 
   * @param exception
   *          the exception thrown in a controller
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   */
  public void handleException(Exception exception, Request request, Response response) {
    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
    response.body("Error handling or redirect...");
  }

  /**
   * Returns error page with HTTP 404 status.
   * 
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   */
  public String handleNotFound(Request request, Response response) {
    response.status(HttpStatus.NOT_FOUND_404);
    Map<String, Object> model = new HashMap<>();
    return render(model, "404.ftl");
  }

  @Override
  public void route() {
    get("*", this::handleNotFound);
    exception(Exception.class, this::handleException);
  }

}
