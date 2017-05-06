package ch.examibur.ui.app.controller;

import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.util.RequestAttributes;
import com.google.inject.Inject;
import java.util.Map;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class ExceptionController implements Controller {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
  private final Renderer engine;

  @Inject
  public ExceptionController(Renderer engine) {
    this.engine = engine;
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

    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
    response.body(engine.render(model, "errors/500.ftl"));

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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    response.status(HttpStatus.NOT_FOUND_404);
    return engine.render(model, "errors/404.ftl");
  }

  /**
   * Returns a 404 error page because a NotFound Exception was thrown.
   * 
   * @param ex
   *          the thrown exception, not used
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   */
  public void handleNotFoundException(Exception ex, Request request, Response response) {
    LOGGER.debug("Returning 404 not found");
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    response.status(HttpStatus.NOT_FOUND_404);
    response.body(engine.render(model, "errors/404.ftl"));
  }

  /**
   * Returns a 403 Error page because an AuthorizedException was thrown.
   * 
   * @param ex
   *          the thrown exception, not used
   * @param request
   *          the HTTP request
   * @param response
   *          the HTTP response
   */
  public void handleAuthorizationException(Exception ex, Request request, Response response) {
    LOGGER.debug("Returning 403 forbidden");
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    response.status(HttpStatus.FORBIDDEN_403);
    response.body(engine.render(model, "errors/403.ftl"));
  }

}
