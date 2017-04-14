package ch.examibur.integration;

import ch.examibur.integration.exam.ExamDao;
import ch.examibur.integration.exam.ExamDaoImpl;
import ch.examibur.integration.examparticipation.ExamParticipationDao;
import ch.examibur.integration.examparticipation.ExamParticipationDaoImpl;
import ch.examibur.integration.exercise.ExerciseDao;
import ch.examibur.integration.exercise.ExerciseDaoImpl;
import ch.examibur.integration.exercisegrading.ExerciseGradingDao;
import ch.examibur.integration.exercisegrading.ExerciseGradingDaoImpl;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDao;
import ch.examibur.integration.exercisesolution.ExerciseSolutionDaoImpl;
import ch.examibur.integration.utils.InitializationException;
import ch.examibur.integration.utils.JdbcCredentials;
import ch.examibur.integration.utils.MigrationLocations;
import ch.examibur.integration.utils.SleepBetweenTriesMs;
import ch.examibur.integration.utils.WaitForDbTimeoutSec;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.flywaydb.core.Flyway;

public class IntegrationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ExamDao.class).to(ExamDaoImpl.class);
    bind(ExerciseDao.class).to(ExerciseDaoImpl.class);
    bind(ExerciseGradingDao.class).to(ExerciseGradingDaoImpl.class);
    bind(ExerciseSolutionDao.class).to(ExerciseSolutionDaoImpl.class);
    bind(ExamParticipationDao.class).to(ExamParticipationDaoImpl.class);

    bindConstant().annotatedWith(WaitForDbTimeoutSec.class).to(3);
    bindConstant().annotatedWith(SleepBetweenTriesMs.class).to(500);

    bind(String[].class).annotatedWith(MigrationLocations.class)
        .toInstance(new String[] { "db/migration" });
  }

  @Provides
  @Singleton
  JdbcCredentials provideJdbcUrl() {
    String host = getNonNullEnv("DB_HOST");
    String user = getNonNullEnv("DB_USER");
    String password = getNonNullEnv("DB_PASSWORD");
    String subProtocoll = "postgresql";
    String driverClass = "org.postgresql.Driver";
    String database = "examibur";
    return new JdbcCredentials(driverClass, subProtocoll, host, database, user, password);
  }

  private String getNonNullEnv(String key) {
    String value = System.getenv().get(key);
    if (value == null) {
      throw new InitializationException("Environment Variable " + key + " is not set!");
    }
    return value;
  }

  @Provides
  EntityManager provideEntityManager(EntityManagerFactory ef) {
    return ef.createEntityManager();
  }

  @Provides
  @Singleton
  EntityManagerFactory provideEntityManagerFactory(JdbcCredentials credentials) {
    Map<String, Object> jdbcProperties = new HashMap<>();
    jdbcProperties.put("javax.persistence.jdbc.url", credentials.getUrl());
    jdbcProperties.put("javax.persistence.jdbc.user", credentials.getUser());
    jdbcProperties.put("javax.persistence.jdbc.password", credentials.getPassword());
    return Persistence.createEntityManagerFactory("examibur", jdbcProperties);
  }

  @Provides
  Flyway provideFlyway(JdbcCredentials credentials, @MigrationLocations String... locations) {
    final Flyway flyway = new Flyway();
    flyway.setDataSource(credentials.getUrl(), credentials.getUser(), credentials.getPassword());
    flyway.setLocations(locations);
    return flyway;
  }

}
