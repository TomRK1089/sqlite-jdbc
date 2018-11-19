module org.sqlite.jdbc {

  requires java.sql;

  exports org.sqlite;
  exports org.sqlite.core;
  exports org.sqlite.date;
  exports org.sqlite.javax;

  provides java.sql.Driver with org.sqlite.JDBC;

}