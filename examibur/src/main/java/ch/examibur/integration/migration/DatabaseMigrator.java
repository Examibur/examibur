package ch.examibur.integration.migration;

import ch.examibur.integration.utils.JdbcCredentialHelper;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

public class DatabaseMigrator {
  private static final String[] SQL_LOCATIONS = new String[]{"db/migration"};
  private final Flyway flyway = new Flyway();

  public DatabaseMigrator() {
    setDefaultDataSource();
    flyway.setLocations(SQL_LOCATIONS);
  }
  
  public DatabaseMigrator(DataSource dataSource, String... locations) {
    flyway.setDataSource(dataSource);
    flyway.setLocations(locations);
  }

  private void setDefaultDataSource() {
    final JdbcCredentialHelper credHelper = new JdbcCredentialHelper();
    flyway.setDataSource(
        credHelper.getJdbcUrl(), credHelper.getDatabaseUser(), credHelper.getDatabasePassword());
  }
  
  /**
   * Migrates the database to the newest version.
   */
  public void migrate() {
    flyway.migrate();
  }
  
  
  /**
   * Drops all entities from the database.
   */
  public void clean() {
    flyway.clean();
  }
}
