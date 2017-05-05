package ch.examibur.ui.app.util;

import javax.servlet.http.Cookie;
import spark.Response;

public final class CookieHelpers {
  public static final String USER_COOKIE = "authentication-token";
  public static final int USER_COOKIE_EXPIRATION = 4 * 60 * 60;
  public static final boolean USER_COOKIE_SECURE = false;
  public static final String USER_COOKIE_PATH = "/";
  public static final boolean USER_COOKIE_HTTP_ONLY = true;

  private CookieHelpers() {
  }

  /**
   * Sets the user authentication cookie.
   * 
   * @param response
   *          The current response
   * @param token
   *          the cookie token to set.
   */
  public static void setUserCookie(Response response, String token) {
    Cookie cookie = new Cookie(CookieHelpers.USER_COOKIE, token);
    cookie.setPath(CookieHelpers.USER_COOKIE_PATH);
    cookie.setHttpOnly(CookieHelpers.USER_COOKIE_HTTP_ONLY);
    cookie.setMaxAge(CookieHelpers.USER_COOKIE_EXPIRATION);
    cookie.setSecure(CookieHelpers.USER_COOKIE_SECURE);
    response.raw().addCookie(cookie);
  }
}
