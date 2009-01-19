//--------------------------------------
// sqlite-jdbc Project
//
// ReadCommitedTest.java
// Since: Jan 19, 2009
//
// $URL$ 
// $Author$
//--------------------------------------
package org.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadUncommittedTest
{
    private Connection conn;
    private Statement stat;

    @BeforeClass
    public static void forName() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
    }

    @Before
    public void connect() throws Exception
    {
        conn = DriverManager.getConnection("jdbc:sqlite:");
        stat = conn.createStatement();
        stat.executeUpdate("create table test (id integer primary key, fn, sn);");
        stat.executeUpdate("create view testView as select * from test;");
    }

    @After
    public void close() throws SQLException
    {
        stat.close();
        conn.close();
    }

    @Test
    public void setReadUncommitted() throws SQLException
    {
        conn.setTransactionIsolation(Conn.TRANSACTION_READ_UNCOMMITTED);
    }

    @Test
    public void setSerializable() throws SQLException
    {
        conn.setTransactionIsolation(Conn.TRANSACTION_SERIALIZABLE);
    }

    @Test(expected = SQLException.class)
    public void setUnsupportedIsolationLevel() throws SQLException
    {
        conn.setTransactionIsolation(Conn.TRANSACTION_REPEATABLE_READ);
    }
}
