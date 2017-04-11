package ch.examibur.integration.utils;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnectionChecker {

  private final int timeoutSec;
  private final int triesMs;

  private final Logger logger = LoggerFactory.getLogger(DbConnectionChecker.class);
  private final JdbcCredentials jdbcCredentials;

  /**
   * @param jdbcCredentials
   *          A Container, that holds all required JDBC Credentials
   * @param timeoutSec
   *          The maximal amount of time to wait for the database to become responsive.
   * @param triesMs
   *          The timeout in seconds to wait between retries.
   */
  @Inject
  public DbConnectionChecker(JdbcCredentials jdbcCredentials, @WaitForDbTimeoutSec int timeoutSec,
      @SleepBetweenTriesMs int triesMs) {
    this.jdbcCredentials = jdbcCredentials;
    this.timeoutSec = timeoutSec;
    this.triesMs = triesMs;
  }

  /**
   * Checks the database connection and blocks until it is either successful or timed out.
   */
  public void checkDatabaseConnection() {
    loadJdbcDriver();

    double spentTimeSec = 0;
    DriverManager.setLoginTimeout(timeoutSec);

    // try connecting until it is either successful or an exception is thrown
    do {
      try {
        tryDbConnect();
        logger.debug("Database connection check successful");
        return;
      } catch (SQLException sqlException) {
        try {

          Thread.sleep(triesMs);
        } catch (InterruptedException interrupt) {
          logger.error("Connection checker failed: Thread interrupted");
          throw new InitializationException(interrupt);
        }
        spentTimeSec += triesMs / 1000.0;
        if (spentTimeSec >= timeoutSec) {
          logger.error("Connection to Database timed out after {} seconds", timeoutSec);
          throw new InitializationException(sqlException);
        }
        logger.info("Retrying database connection");
      }
    } while (true);

  }

  private void loadJdbcDriver() {
    try {
      Class.forName(jdbcCredentials.getDriverClass());
    } catch (ClassNotFoundException classLoadException) {
      logger.error("Failed to load JDBC driver {}", jdbcCredentials.getDriverClass());
      throw new InitializationException(classLoadException);
    }
  }

  private void tryDbConnect() throws SQLException {
    Connection con = null;
    try {
      con = DriverManager.getConnection(jdbcCredentials.getUrl(), jdbcCredentials.getUser(),
          jdbcCredentials.getPassword());
    } finally {
      if (con != null) {
        con.close();
      }
    }

  }

}
