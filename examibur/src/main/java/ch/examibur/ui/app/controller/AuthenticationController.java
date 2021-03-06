package ch.examibur.ui.app.controller;

import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.model.AuthenticationInformation;
import ch.examibur.ui.app.render.Renderer;
import ch.examibur.ui.app.url.Link;
import ch.examibur.ui.app.url.QueryParameter;
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
    Map<String, Object> model = request.attribute(RequestAttributes.MODEL);
    return engine.render(model, "views/loginView.ftlh");
  }

  /**
   * Tries to log in the user with the credentials provided in the requests form-data. On Success,
   * the user is redirected to the Dashboard. Otherwise, he is redirected to the login page.
   */
  public String performLogin(Request request, Response response) {
    String username = request.raw().getParameter(USERNAME_PARAMETER);
    String password = request.raw().getParameter(PASSWORD_PARAMETER);
    try {
      AuthenticationInformation login = authenticationService.login(username, password);

      CookieHelpers.setUserCookie(response, login.getToken());

      String ref = Link.toDashboard();
      if (request.queryParams(QueryParameter.REF.toString()) != null) {
        ref = request.queryParams(QueryParameter.REF.toString());
      }

      response.redirect(ref);
    } catch (ExamiburException e) {
      response.redirect(Link.toLogin());
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
    response.redirect(Link.toLogin());
    return null;
  }

  /**
   * Add / in the breadcrumbs.
   */
  public void addBreadCrumb(Request request, Response response) {
    RequestHelper.pushBreadCrumb(request, "Login", Link.toLogin());
  }
}
