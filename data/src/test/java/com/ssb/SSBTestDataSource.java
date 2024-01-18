package com.ssb;

import com.ssb.data.*;

import java.sql.*;

public class SSBTestDataSource extends SSBDataSource{
    private static SSBTestDataSource dataSource;
    protected SSBTestDataSource() throws ClassNotFoundException {
        super();
    }

    protected Connection getSSBConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ssb_test",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new SSBTestDataSource();
        }
        return dataSource.getSSBConnection();
    }
}
