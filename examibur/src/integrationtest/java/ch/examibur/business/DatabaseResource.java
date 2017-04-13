package ch.examibur.business;

import static ch.examibur.business.IntegrationTestUtil.INJECTOR;

import ch.examibur.integration.utils.InitializationException;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseResource extends ExternalResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseResource.class);

  private final Flyway dbMigrator = INJECTOR.getInstance(Flyway.class);

  @Override
  protected void before() {
    try {
      LOGGER.info("Clean and migrate database");
      dbMigrator.clean();
      dbMigrator.migrate();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed", ex);
      throw new InitializationException(ex);
    }
  }

  @Override
  protected void after() {
    try {
      LOGGER.info("Clean database");
      dbMigrator.clean();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed", ex);
      throw new InitializationException(ex);
    }
  }

}
