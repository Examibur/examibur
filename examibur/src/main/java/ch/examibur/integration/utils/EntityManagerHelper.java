package ch.examibur.integration.utils;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum EntityManagerHelper {
  INSTANCE;
  
  private static final String PERSISTENCE_UNIT_NAME = "examibur";
  
  private static final String JDBC_URL_PROPERTY = "javax.persistence.jdbc.url";
  private static final String JDBC_USER_PROPERTY = "javax.persistence.jdbc.user";
  private static final String JDBC_PASSWORD_PROPERTY = "javax.persistence.jdbc.password";

  private final EntityManagerFactory factory;

  private EntityManagerHelper() {
    Map<String, Object> properties = getJdbcProperties();
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
  }

  /**
   * @return an EntityManager to query the database.
   */
  public EntityManager createEntityManager() {
    return factory.createEntityManager();
  }
  
  private Map<String, Object> getJdbcProperties() {
    JdbcCredentialHelper credentialHelper = new JdbcCredentialHelper();
    Map<String, Object> jdbcProperties = new HashMap<>();
    
    jdbcProperties.put(JDBC_URL_PROPERTY, credentialHelper.getJdbcUrl());
    jdbcProperties.put(JDBC_USER_PROPERTY, credentialHelper.getDatabaseUser());
    jdbcProperties.put(JDBC_PASSWORD_PROPERTY, credentialHelper.getDatabasePassword());
    
    return jdbcProperties;
  }
}
