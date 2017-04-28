package ch.examibur.service.model;

import ch.examibur.domain.User;

public class AuthenticationInformation {

  private User user;
  private String token;

  public AuthenticationInformation(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
