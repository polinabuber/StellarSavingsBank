package com.ssb.data;

import java.sql.*;

public class SSBDataSource {
    private static SSBDataSource dataSource;
    protected SSBDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    protected Connection getSSBConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ssb",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            dataSource = new SSBDataSource();
        }
        return dataSource.getSSBConnection();
    }
}
