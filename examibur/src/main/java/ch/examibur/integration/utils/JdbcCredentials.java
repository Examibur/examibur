package ch.examibur.integration.utils;

public final class JdbcCredentials {

  private final String driverClass;
  private final String subProtocoll;
  private final String host;
  private final String database;
  private final String user;
  private final String password;

  /**
   * @param driverClass
   *          The fully qualified class name of the driver to load
   * @param subProtocoll
   *          the JDBS sub protocol - eg. postgres.
   * @param host
   *          the database host.
   * @param database
   *          the database name.
   * @param user
   *          the database username.
   * @param password
   *          the database password.
   */
  public JdbcCredentials(String driverClass, String subProtocoll, String host, String database,
      String user, String password) {
    super();
    this.driverClass = driverClass;
    this.subProtocoll = subProtocoll;
    this.host = host;
    this.database = database;
    this.user = user;
    this.password = password;
  }

  public String getUrl() {
    return "jdbc:" + subProtocoll + "://" + host + "/" + database;
  }

  public String getDriverClass() {
    return driverClass;
  }

  public String getSubProtocoll() {
    return subProtocoll;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

}
