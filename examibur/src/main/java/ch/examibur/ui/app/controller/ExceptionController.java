package ch.examibur.ui.app.controller;

import static ch.examibur.ui.app.util.TemplateUtil.render;
import static spark.Spark.exception;
import static spark.Spark.get;

import ch.examibur.integration.SingleResultNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;

public class ExceptionController extends Controller {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);

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
    LOGGER.error("Caught unhandled exception", exception);

    Map<String, Object> model = new HashMap<>();
    response.body(render(model, "500.ftl"));
    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);

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
  
  private void handleNotFoundException(Exception ex, Request request, Response response) {
    LOGGER.debug("Returning 404 not found");
    Map<String, Object> model = new HashMap<>();
    response.body(render(model, "404.ftl"));
    response.status(HttpStatus.NOT_FOUND_404);
  }

  @Override
  public void route() {
    get("*", this::handleNotFound); 
    exception(SingleResultNotFoundException.class, this::handleNotFoundException);
    exception(Exception.class, this::handleException);
  }

}
