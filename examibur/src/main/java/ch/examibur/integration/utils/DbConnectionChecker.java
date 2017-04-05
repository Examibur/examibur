package ch.examibur.integration.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnectionChecker {

  private static final int SQL_TIMEOUT_SEC = 3;
  private static final int SLEEP_BETWEEN_TRIES_MS = 500;
  private static final String JDBC_DRIVER = "org.postgresql.Driver";

  private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionChecker.class);

  /**
   * Checks the database connection and blocks until it is either successful or timed out.
   */
  public void checkDatabaseConnection() {
    loadJdbcDriver();

    double spentTimeSec = 0;
    JdbcCredentialHelper credHelper = new JdbcCredentialHelper();
    DriverManager.setLoginTimeout(SQL_TIMEOUT_SEC);

    // try connecting until it is either successful or an exception is thrown
    do {
      try {
        tryDbConnect(credHelper);
        LOGGER.debug("Database connection check successful");
        return;
      } catch (SQLException sqlException) {
        try {
          
          Thread.sleep(SLEEP_BETWEEN_TRIES_MS);
        } catch (InterruptedException interrupt) {
          LOGGER.error("Connection checker failed: Thread interrupted");
          throw new InitializationException(interrupt);
        }
        spentTimeSec += SLEEP_BETWEEN_TRIES_MS / 1000.0;
        if (spentTimeSec >= SQL_TIMEOUT_SEC) {
          LOGGER.error("Connection to Database timed out after " + SQL_TIMEOUT_SEC + " seconds");
          throw new InitializationException(sqlException);
        }
        LOGGER.info("Retrying database connection");
      }
    } while (true);

  }

  private void loadJdbcDriver() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException classLoadException) {
      LOGGER.error("Failed to load JDBC driver " + JDBC_DRIVER);
      throw new InitializationException(classLoadException);
    }
  }

  private void tryDbConnect(JdbcCredentialHelper credHelper) throws SQLException {
    Connection con = null;
    try {
      con = DriverManager.getConnection(credHelper.getJdbcUrl(), credHelper.getDatabaseUser(),
          credHelper.getDatabasePassword());
    } finally {
      if (con != null) {
        con.close();
      }
    }

  }

}
