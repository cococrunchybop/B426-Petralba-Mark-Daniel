package com.joysistvi.recordingapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final String url = "jdbc:mysql://localhost:3306/recording_studio_db";
    private final String username = "root";
    private final String password = "root@123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
