package com.joysistvi.recordingapp.main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final static String URL = "jdbc:mysql://localhost:3306/recording_studio_db";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root@123";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /** Turns raw MySQL errors into short messages a console user can understand. */
    public static String friendlyMessage(SQLException e) {
        switch (e.getErrorCode()) {
            case 1451:
                return "This record is still used by other records (songs, albums, playlists...). "
                        + "Remove or archive those first, or archive this record instead.";
            case 1452:
                return "The related ID you entered does not exist (check the artist/album/user ID).";
            case 1062:
                return "That value already exists (username or email must be unique).";
            case 1406:
                return "One of the values is too long.";
            default:
                break;
        }
        String state = e.getSQLState();
        if (state != null && state.startsWith("08")) {
            return "Cannot reach the database. Make sure MySQL is running and the settings in DbConnection are correct.";
        }
        return e.getMessage();
    }
}
