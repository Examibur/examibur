package ch.examibur.business.service;

import ch.examibur.business.DatabaseResource;
import ch.examibur.business.IntegrationTestUtil;
import ch.examibur.domain.User;
import ch.examibur.service.AuthenticationService;
import ch.examibur.service.exception.ExamiburException;
import ch.examibur.service.exception.InvalidParameterException;
import ch.examibur.service.model.AuthenticationInformation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class AuthenticationServiceImplTest {

  @Rule
  public final DatabaseResource res = new DatabaseResource();

  private final AuthenticationService authenticationService =
      IntegrationTestUtil.getInjector().getInstance(AuthenticationService.class);

  private static final User USER_MAXIMILIAN_MUELLER =
      new User(1L, "Maximilian", "Mueller", "maximilian.mueller");

  @Test
  public void testLoginWithToken() throws ExamiburException {
    String token = USER_MAXIMILIAN_MUELLER.getUsername();
    AuthenticationInformation info = authenticationService.login(token);
    Assert.assertEquals(token, info.getToken());
    assertUser(USER_MAXIMILIAN_MUELLER, info.getUser());
  }

  @Test
  public void testLoginWithUsername() throws ExamiburException {
    String username = USER_MAXIMILIAN_MUELLER.getUsername();
    String password = "***";
    AuthenticationInformation info = authenticationService.login(username, password);
    Assert.assertEquals(username, info.getToken());
    assertUser(USER_MAXIMILIAN_MUELLER, info.getUser());
  }

  @Test
  public void testLoginWithInvalidUsername() throws ExamiburException {
    String username = "invalid";
    String password = "***";

    try {
      authenticationService.login(username, password);
      Assert.fail("Expected exception!");
    } catch (InvalidParameterException e) {
      Assert.assertEquals("Invalid username / password.", e.getMessage());
    }

  }

  @Test
  public void testLoginWithNullUsername() throws ExamiburException {
    String username = null;
    String password = null;

    try {
      authenticationService.login(username, password);
      Assert.fail("Expected exception!");
    } catch (InvalidParameterException e) {
      Assert.assertEquals("Invalid username / password.", e.getMessage());
    }

  }

  private void assertUser(User expected, User actual) {
    Assert.assertEquals(expected.getUsername(), actual.getUsername());
    Assert.assertEquals(expected.getId(), actual.getId());
    Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
    Assert.assertEquals(expected.getLastName(), actual.getLastName());

  }

}
