package ch.examibur.integration.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerHelper {
  INSTANCE;
  
  private static final String PERSISTENCE_UNIT_NAME = "examibur";
  private static final String DB_NAME = "examibur";
  private static final String ENV_DB_HOST = "DB_HOST";
  private static final String ENV_DB_USER = "DB_USER";
  private static final String ENV_DB_PASSWORD = "DB_PASSWORD";

  private final EntityManagerFactory factory;

  private EntityManagerHelper() {
    Map<String, Object> properties = getJdbcCredentials();
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
  }

  /**
   * @return an EntityManager to query the database.
   */
  public EntityManager createEntityManager() {
    return factory.createEntityManager();
  }
  
  private Map<String, Object> getJdbcCredentials() {
    Map<String, String> env = System.getenv();
    Map<String, Object> jdbcCredentials = new HashMap<>();
    String dbUrl = null;
    String dbUser = null;
    String dbPassword = null;
    
    for (String envName : env.keySet()) {
      if (envName.contains(ENV_DB_HOST)) {
        dbUrl = "jdbc:postgresql://" + env.get(envName) + "/" + DB_NAME;
      } else if (envName.contains(ENV_DB_USER)) {
        dbUser = env.get(envName);
      } else if (envName.contains(ENV_DB_PASSWORD)) {
        dbPassword = env.get(envName);
      }
    }
    if (dbUrl == null || dbUser == null || dbPassword == null) {
      throw new Error("Environment variables for database connection are missing");
    }
    
    jdbcCredentials.put("javax.persistence.jdbc.url", dbUrl);
    jdbcCredentials.put("javax.persistence.jdbc.user", dbUser);
    jdbcCredentials.put("javax.persistence.jdbc.password", dbPassword);


    return jdbcCredentials;
  }
}
