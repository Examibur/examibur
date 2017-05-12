package ch.examibur.ui.app.filter;

import ch.examibur.business.util.AuthenticationUtil;
import ch.examibur.domain.User;
import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.model.AuthenticationInformation;
import ch.examibur.ui.app.routing.QueryParameter;
import ch.examibur.ui.app.routing.RouteBuilder;
import ch.examibur.ui.app.util.BreadCrumbEntry;
import ch.examibur.ui.app.util.CookieHelpers;
import ch.examibur.ui.app.util.RequestAttributes;
import com.google.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Spark;

public class Filters {

  private AuthenticationService authenticationService;

  @Inject
  public Filters(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
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
  public void addTrailingSlashes(Request request, Response response) {
    if (!request.pathInfo().endsWith("/")) {
      response.redirect(request.pathInfo() + "/");
      Spark.halt();
    }
  }

  public void utf8Encoding(Request request, Response response) throws UnsupportedEncodingException {
    request.raw().setCharacterEncoding("UTF-8");
    response.raw().setCharacterEncoding("UTF-8");
  }

  /**
   * Populates the model with all globally required/available fields to simplify rendering.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   */
  public void addBaseModel(Request request, Response response) {
    Map<String, Object> baseModel = new HashMap<>();
    baseModel.put(RequestAttributes.USER, request.attribute(RequestAttributes.USER));
    baseModel.put(RequestAttributes.TITLE, "Examibur");
    baseModel.put(RequestAttributes.BREADCRUMB, new LinkedList<BreadCrumbEntry>());
    baseModel.put(RequestAttributes.URL, request.uri());
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
  public void handleAuthentication(Request request, Response response) throws ExamiburException {
    AuthenticationUtil.setCurrentUser(null);

    String token = request.cookie(CookieHelpers.USER_COOKIE);
    if (token == null) {
      redirectToLoginPage(request, response);
    }
    try {
      AuthenticationInformation info = authenticationService.login(token);
      User user = info.getUser();
      AuthenticationUtil.setCurrentUser(user);
      request.attribute(RequestAttributes.USER, user);

      if (request.uri().startsWith(RouteBuilder.toLogin())) {
        response.redirect(RouteBuilder.toDashboard());
        Spark.halt();
      }
    } catch (InvalidParameterException e) {
      redirectToLoginPage(request, response);
    }
  }

  private void redirectToLoginPage(Request request, Response response) {
    if (!request.uri().startsWith(RouteBuilder.toLogin())) {
      String redirectUrl = RouteBuilder.addQueryParameter(RouteBuilder.toLogin(),
          QueryParameter.REF.toString(), request.uri());
      response.redirect(redirectUrl);
      Spark.halt();
    }
  }

  /**
   * Enable GZIP for all responses.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The current response.
   */
  public void addGzipHeader(Request request, Response response) {
    response.header("Content-Encoding", "gzip");
  }

}
