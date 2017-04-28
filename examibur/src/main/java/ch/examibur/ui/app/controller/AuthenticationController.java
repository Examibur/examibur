package ch.examibur.ui.app.controller;

import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.model.AuthenticationInformation;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.util.CookieHelpers;
import ch.examibur.ui.app.util.RequestAttributes;
import ch.examibur.ui.app.util.RequestHelper;
import com.google.inject.Inject;
import java.util.Map;
import spark.Request;
import spark.Response;

public class AuthenticationController implements Controller {

  private static final String USERNAME_PARAMETER = "username";
  private static final String PASSWORD_PARAMETER = "password";

  private final AuthenticationService authenticationService;
  private final Renderer engine;

  @Inject
  public AuthenticationController(Renderer engine, AuthenticationService authenticationService) {
    this.engine = engine;
    this.authenticationService = authenticationService;
  }

  public String displayLoginForm(Request request, Response response) throws ExamiburException {
    // TODO: redirect if the user is logged-in.
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "login.ftl");
  }

  /**
   * Tries to log in the user with the credentials provided in the requests form-data. On Success,
   * the user is redirected to the Dashboard. Otherwise, he is redirected to the login page.
   */
  public String performLogin(Request request, Response response) throws ExamiburException {
    String username = request.raw().getParameter(USERNAME_PARAMETER);
    String password = request.raw().getParameter(PASSWORD_PARAMETER);
    try {
      AuthenticationInformation login = authenticationService.login(username, password);
      response.cookie(CookieHelpers.USER_COOKIE_PATH, CookieHelpers.USER_COOKIE, login.getToken(),
          CookieHelpers.USER_COOKIE_EXPIRATION, CookieHelpers.USER_COOKIE_SECURE);

      String ref = RouteBuilder.toDashboard();
      if (request.queryParams("ref") != null) {
        ref = request.queryParams("ref");
      }

      response.redirect(ref);
    } catch (ExamiburException e) {
      response.redirect(RouteBuilder.toLogin());
    }
    return null;
  }

  /**
   * Logs out the current user by invalidating it's cookie.
   */
  public String logout(Request request, Response response) {
    authenticationService.logout(request.cookie(CookieHelpers.USER_COOKIE));
    response.cookie(CookieHelpers.USER_COOKIE_PATH, CookieHelpers.USER_COOKIE, "invalid", 0,
        CookieHelpers.USER_COOKIE_SECURE);
    response.redirect(RouteBuilder.toLogin());
    return null;
  }

  /**
   * Add / in the breadcrumbs.
   */
  public void addBreadCrumb(Request request, Response response) {
    RequestHelper.pushBreadCrumb(request, "Login", RouteBuilder.toLogin());
  }
}
