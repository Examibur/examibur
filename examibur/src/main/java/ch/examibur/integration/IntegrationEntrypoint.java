package ch.examibur.integration;

import ch.examibur.integration.utils.DbConnectionChecker;
import ch.examibur.integration.utils.InitializationException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationEntrypoint implements Entrypoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationEntrypoint.class);
  private Injector injector;

  @Override
  public Injector init() {
    injector = Guice.createInjector(new IntegrationModule());
    checkDatabaseConnection();
    migrateDatabase();
    return injector;
  }

  private void checkDatabaseConnection() {
    DbConnectionChecker checker = injector.getInstance(DbConnectionChecker.class);
    checker.checkDatabaseConnection();
  }

  private void migrateDatabase() {
    final Flyway dbMigrator = injector.getInstance(Flyway.class);
    try {
      dbMigrator.migrate();
    } catch (FlywayException ex) {
      LOGGER.error("Initial Database Migration failed");
      throw new InitializationException(ex);
    }
  }
}
