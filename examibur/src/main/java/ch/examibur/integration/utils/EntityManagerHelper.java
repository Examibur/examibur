package ch.examibur.integration.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerHelper {
  INSTANCE;
  
  private static final String PERSISTANCE_UNIT_NAME = "examibur";
  private static final String DB_NAME = "examibur";
  private static final String ENV_DB_HOST = "DB_HOST";
  private static final String ENV_DB_USER = "DB_USER";
  private static final String ENV_DB_PASSWORD = "DB_PASSWORD";

  private final EntityManagerFactory factory;

  private EntityManagerHelper() {
    Map<String, Object> properties = getJdbcCredentials();
    factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME, properties);
  }

  public EntityManager createEntityManager() {
    return factory.createEntityManager();
  }
  
  private Map<String, Object> getJdbcCredentials() {
    Map<String, String> env = System.getenv();
    Map<String, Object> jdbcCredentials = new HashMap<>();
    
    for (String envName : env.keySet()) {
      if (envName.contains(ENV_DB_HOST)) {
        String url = "jdbc:postgresql://" + env.get(envName) + "/" + DB_NAME;
        jdbcCredentials.put("javax.persistence.jdbc.url", url);
      } else if (envName.contains(ENV_DB_USER)) {
        jdbcCredentials.put("javax.persistence.jdbc.user", env.get(envName));
      } else if (envName.contains(ENV_DB_PASSWORD)) {
        jdbcCredentials.put("javax.persistence.jdbc.password", env.get(envName));
      }
    }
    return jdbcCredentials;
  }
}
