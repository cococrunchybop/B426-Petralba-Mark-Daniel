package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository {

    private final DbConnection dbConnection;

    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Artist> findAllActive() {
        String query = "SELECT id, name, is_archived FROM artists WHERE is_archived = 0 ORDER BY id";
        List<Artist> artists = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                artists.add(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("is_archived")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Read Artists Error: " + e.getMessage(), e);
        }

        return artists;
    }

    @Override
    public boolean save(Artist artist) {
        String query = "INSERT INTO artists (name, is_archived) VALUES (?, 0)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artist.getName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException("Create Artist Error: " + e.getMessage(), e);
        }
    }

}
