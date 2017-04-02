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

  final Logger logger = LoggerFactory.getLogger(DbConnectionChecker.class);

  /**
   * Checks the database connection and blocks until it is either successful or timed out.
   */
  public void checkDatabaseConnection() {
    loadJdbcDriver();

    double spentTimeSec = 0;
    JdbcCredentialHelper credHelper = new JdbcCredentialHelper();
    DriverManager.setLoginTimeout(SQL_TIMEOUT_SEC);

    do {
      try {
        tryDbConnect(credHelper);
        break;
      } catch (SQLException sqlException) {
        try {
          Thread.sleep(SLEEP_BETWEEN_TRIES_MS);
        } catch (InterruptedException e) {
          logger.debug("Sleep interrupted");
        }
        spentTimeSec += SLEEP_BETWEEN_TRIES_MS / 1000.0;
        if (spentTimeSec >= SQL_TIMEOUT_SEC) {
          logger.error("Connection to Database timed out after " + SQL_TIMEOUT_SEC + " seconds");
          throw new InitializationException(sqlException);
        }
        logger.info("Retrying database connection");
      }
    } while (spentTimeSec < SQL_TIMEOUT_SEC);
    logger.debug("Database connection check successful");
  }

  private void loadJdbcDriver() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException classLoadException) {
      logger.error("Failed to load JDBC driver " + JDBC_DRIVER);
      throw new InitializationException(classLoadException);
    }
  }

  private void tryDbConnect(JdbcCredentialHelper credHelper) throws SQLException {
    Connection con = DriverManager.getConnection(credHelper.getJdbcUrl(),
        credHelper.getDatabaseUser(), credHelper.getDatabasePassword());
    con.close();
  }

}
