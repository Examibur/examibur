package ch.examibur.service;

import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.model.AuthenticationInformation;

public interface AuthenticationService {

  /**
   * Tries to log in the given user with the given password.
   * 
   * @throws ExamiburException
   *           a {@link InvalidParameterException} is thrown if the user can not be authenticated.
   */
  AuthenticationInformation login(String username, String password) throws ExamiburException;

  /**
   * Tries to log in the user with the given authentication token (a cookie or REST-Authentication
   * token)
   * 
   * @throws ExamiburException
   *           a {@link InvalidParameterException} if the given token is invalid.
   */
  AuthenticationInformation login(String authenticationToken) throws ExamiburException;

  /**
   * Invalidates the given authentication token. This method does not provide any feedback weather a
   * token was actually deleted for security reasons.
   */
  void logout(String authenticationToken);

}