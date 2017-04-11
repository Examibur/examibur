package ch.examibur.business;

import ch.examibur.integration.migration.DatabaseMigrator;
import ch.examibur.integration.utils.InitializationException;
import org.flywaydb.core.api.FlywayException;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestResource extends ExternalResource {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(TestResource.class);
  
  private final DatabaseMigrator dbMigrator = new DatabaseMigrator();
  
  protected void before() {
    try {
      LOGGER.info("Clean and migrate database");
      dbMigrator.clean();
      dbMigrator.migrate();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed");
      throw new InitializationException(ex);
    }
  }

  protected void after() {
    try {
      LOGGER.info("Clean database");
      dbMigrator.clean();
    } catch (FlywayException ex) {
      LOGGER.error("Clean and/or Initial Database Migration failed");
      throw new InitializationException(ex);
    }
  }
  
}
