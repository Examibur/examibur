package ch.examibur.integration;

import ch.examibur.integration.migration.DatabaseMigrator;
import ch.examibur.integration.utils.DbConnectionChecker;
import ch.examibur.integration.utils.InitializationException;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationEntrypoint implements Entrypoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationEntrypoint.class);
  
  @Override
  public void init() {
    checkDatabaseConnection();
    migrateDatabase();
  }

  private void checkDatabaseConnection() {
    final DbConnectionChecker conChecker = new DbConnectionChecker();
    conChecker.checkDatabaseConnection();
  }
  
  private void migrateDatabase() {
    final DatabaseMigrator dbMigrator = new DatabaseMigrator();
    try {
      dbMigrator.migrate();
    } catch (FlywayException ex) {
      LOGGER.error("Initial Database Migration failed");
      throw new InitializationException(ex);
    }
  }
}
