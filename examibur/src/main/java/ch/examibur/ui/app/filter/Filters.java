package ch.examibur.ui.app.filter;

import ch.examibur.business.util.AuthenticationUtil;
import ch.examibur.domain.User;
import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.IllegalOperationException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.model.AuthenticationInformation;
import ch.examibur.ui.app.model.Message;
import ch.examibur.ui.app.model.MessageType;
import ch.examibur.ui.app.url.Link;
import ch.examibur.ui.app.url.QueryParameter;
import ch.examibur.ui.app.util.BreadCrumbEntry;
import ch.examibur.ui.app.util.CookieHelpers;
import ch.examibur.ui.app.util.RequestAttributes;
import com.google.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

public class Filters {

  private static final String CHARACTER_ENCODING = "UTF-8";

  private static final Logger LOGGER = LoggerFactory.getLogger(Filters.class);

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
    request.raw().setCharacterEncoding(CHARACTER_ENCODING);
    response.raw().setCharacterEncoding(CHARACTER_ENCODING);
  }

  /**
   * Populates the model with all globally required/available fields to simplify rendering.
   * 
   * @param request
   *          The current request.
   * @param response
   *          The planned response.
   * @throws ExamiburException
   *           throws an {@link IllegalOperationException} if the url decoding fails.
   */
  public void addBaseModel(Request request, Response response) throws ExamiburException {
    Map<String, Object> baseModel = new HashMap<>();
    baseModel.put(RequestAttributes.USER, request.attribute(RequestAttributes.USER));
    baseModel.put(RequestAttributes.TITLE, "Examibur");
    baseModel.put(RequestAttributes.BREADCRUMB, new LinkedList<BreadCrumbEntry>());
    baseModel.put(RequestAttributes.URL, request.uri());
    addNotificationMessage(baseModel, request);
    request.attribute(RequestAttributes.MODEL, baseModel);
  }

  private void addNotificationMessage(Map<String, Object> baseModel, Request request)
      throws ExamiburException {
    String message = request.queryParams(QueryParameter.NOTIFICATION_MESSAGE.toString());
    MessageType messageType =
        MessageType.forName(request.queryParams(QueryParameter.NOTIFICATION_TYPE.toString()));
    if (message != null && !message.isEmpty() && messageType != null) {
      try {
        message = URLDecoder.decode(message, CHARACTER_ENCODING);
      } catch (UnsupportedEncodingException e) {
        LOGGER.error("Error while decoding the query parameter!", e);
        IllegalOperationException illegalOperationException =
            new IllegalOperationException("Error while decoding the query parameter "
                + QueryParameter.NOTIFICATION_MESSAGE.toString());
        LOGGER.error(illegalOperationException.getMessage(), illegalOperationException);
        throw illegalOperationException;
      }
      baseModel.put(RequestAttributes.NOTIFICATION_MESSAGE, new Message(message, messageType));
    }
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

      if (request.uri().startsWith(Link.toLogin())) {
        response.redirect(Link.toDashboard());
        Spark.halt();
      }
    } catch (InvalidParameterException e) {
      redirectToLoginPage(request, response);
    }
  }

  private void redirectToLoginPage(Request request, Response response) {
    if (!request.uri().startsWith(Link.toLogin())) {
      String redirectUrl =
          Link.addQueryParameter(Link.toLogin(), QueryParameter.REF, request.uri());
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
