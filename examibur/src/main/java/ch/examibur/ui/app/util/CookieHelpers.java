package ch.examibur.ui.app.util;

public final class CookieHelpers {
  public static final String USER_COOKIE = "authentication-token";
  public static final int USER_COOKIE_EXPIRATION = 4 * 60 * 60;
  public static final boolean USER_COOKIE_SECURE = false;
  public static final String USER_COOKIE_PATH = "/";
  public static final boolean USER_COOKIE_HTTP_ONLY = true;

  private CookieHelpers() {
  }
}
