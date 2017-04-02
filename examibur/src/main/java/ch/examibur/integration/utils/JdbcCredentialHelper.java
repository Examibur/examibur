package ch.examibur.integration.utils;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper for retrieving database credentials from the system environment. 
 * @author Robin Suter
 */
public class JdbcCredentialHelper {

  private static final String JDBC_DRIVER = "postgresql";
  private static final String DB_NAME = "examibur";
  
  private static final String ENV_DB_HOST = "DB_HOST";
  private static final String ENV_DB_USER = "DB_USER";
  private static final String ENV_DB_PASSWORD = "DB_PASSWORD";

  private static final Logger logger = LoggerFactory.getLogger(JdbcCredentialHelper.class);

  private final Map<String, String> envMap;
  
  
  /**
   * @param environmentMap
   *  The system environment to use (default: {@link System#getenv}).
   */
  public JdbcCredentialHelper(Map<String, String> environmentMap) {
    this.envMap = environmentMap;
  }
  
  public JdbcCredentialHelper() {
    this(System.getenv());
  }
  
  private String getEnvironmentVariable(String name) {
    for (Map.Entry<String, String> envEntry : envMap.entrySet()) {
      if (envEntry.getKey().contains(name)) {
        return envEntry.getValue();
      }
    }
    logger.error("Environment variable " + name + " is missing from system");
    throw new InitializationException("Environment variable " + name + " not set");
  }
  
  public String getJdbcUrl() {
    return "jdbc:" + JDBC_DRIVER + "://" + getDatabaseHost() + "/" + DB_NAME;
  }
  
  public String getDatabaseHost() {
    return getEnvironmentVariable(ENV_DB_HOST);
  }
  
  public String getDatabaseUser() {
    return getEnvironmentVariable(ENV_DB_USER);
  }
  
  public String getDatabasePassword() {
    return getEnvironmentVariable(ENV_DB_PASSWORD);
  }
}
