package ch.examibur.integration.user;

import ch.examibur.domain.User;

public interface UserDao {

  User getUser(String username);

}
