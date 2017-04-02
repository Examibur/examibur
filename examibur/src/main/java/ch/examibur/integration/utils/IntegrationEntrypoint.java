package ch.examibur.integration.utils;

import ch.examibur.integration.migration.DatabaseMigrator;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationEntrypoint implements Entrypoint {

  final Logger logger = LoggerFactory.getLogger(IntegrationEntrypoint.class);
  
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
      logger.error("Initial Database Migration failed");
      throw new InitializationException(ex);
    }
  }
}
