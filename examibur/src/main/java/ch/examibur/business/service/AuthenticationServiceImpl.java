package ch.examibur.business.service;

import ch.examibur.domain.User;
import ch.examibur.integration.user.UserDao;
import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.model.AuthenticationInformation;
import com.google.inject.Inject;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationServiceImpl implements AuthenticationService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

  private final UserDao userDao;

  @Inject
  public AuthenticationServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public AuthenticationInformation login(String username, String password)
      throws ExamiburException {
    try {
      User user = userDao.getUser(username);
      // In this stage, the user token is the username
      return new AuthenticationInformation(user, username);
    } catch (NoResultException e) {
      LOGGER.info("Invalid loggin attempt for non-existing user " + username);
      throw new InvalidParameterException("Invalid username / password.", e);
    }
  }

  @Override
  public AuthenticationInformation login(String authenticationToken) throws ExamiburException {
    // AuthorizationException
    // In this stage, the user token is the username
    return login(authenticationToken, null);
  }

  @Override
  public void logout(String authenticationToken) {
    // This method will delete the authentication token when they are actually generated.
  }

}
