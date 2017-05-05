package ch.examibur.integration.user;

import ch.examibur.domain.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserDaoImpl implements UserDao {

  private final Provider<EntityManager> entityManagerProvider;

  @Inject
  public UserDaoImpl(Provider<EntityManager> entityManagerProvider) {
    this.entityManagerProvider = entityManagerProvider;
  }

  @Override
  public User getUser(String username) {
    EntityManager entityManager = entityManagerProvider.get();
    try {
      TypedQuery<User> userQuery = entityManager
          .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
      return userQuery.setParameter("username", username).getSingleResult();
    } finally {
      entityManager.close();
    }
  }

}
