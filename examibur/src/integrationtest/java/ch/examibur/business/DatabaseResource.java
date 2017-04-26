package ch.examibur.business;

import ch.examibur.integration.utils.InitializationException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseResource extends ExternalResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseResource.class);

  private Flyway dbMigrator;

  private Flyway getDbMigrator() {
    if (dbMigrator == null) {
      dbMigrator = IntegrationTestUtil.getInjector().getInstance(Flyway.class);
    }
    return dbMigrator;
  }

  @Override
  protected void before() {
    try {
      LOGGER.info("Clean and migrate database");
      getDbMigrator().clean();
      getDbMigrator().migrate();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed", ex);
      throw new InitializationException(ex);
    }
  }

  @Override
  protected void after() {
    try {
      LOGGER.info("Clean database");
      getDbMigrator().clean();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed", ex);
      throw new InitializationException(ex);
    }
  }

}
