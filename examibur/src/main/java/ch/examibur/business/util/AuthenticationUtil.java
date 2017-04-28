package ch.examibur.business.util;

import ch.examibur.domain.User;

public class AuthenticationUtil {

  private static final ThreadLocal<User> context = new ThreadLocal<>();

  private AuthenticationUtil() {
  }

  public static User getCurrentUser() {
    return context.get();
  }

  public static void setCurrentUser(User user) {
    context.set(user);
  }

}
