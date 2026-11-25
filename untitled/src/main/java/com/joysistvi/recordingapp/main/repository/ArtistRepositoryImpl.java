package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.Artist;

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

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                artists.add(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("is_archived")));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + DbConnection.friendlyMessage(e));
        }

        return artists;
    }

    @Override
    public List<Artist> findAllArchived() {
        String query = "SELECT id, name, is_archived FROM artists WHERE is_archived = 1 ORDER BY id";
        List<Artist> artists = new ArrayList<>();

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                artists.add(new Artist(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("is_archived")));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + DbConnection.friendlyMessage(e));
        }

        return artists;
    }

    @Override
    public boolean save(Artist artist) {
        String query = "INSERT INTO artists (name, is_archived) VALUES (?, 0)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artist.getName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean update(Artist artist) {
        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artist.getName());
            preparedStatement.setInt(2, artist.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (deleteById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean archiveById(int id) {
        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (archiveById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean restoreById(int id) {
        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (restoreById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public List<Artist> searchByName(String keyword) {
        String query = "SELECT id, name, is_archived FROM artists " +
                "WHERE is_archived = 0 AND name LIKE ? " +
                "ORDER BY id";
        List<Artist> artists = new ArrayList<>();

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    artists.add(new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBoolean("is_archived")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByName): " + DbConnection.friendlyMessage(e));
        }

        return artists;
    }

    @Override
    public Artist findById(int id) {
        String query = "SELECT id, name, is_archived FROM artists WHERE id = ? AND is_archived = 0";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Artist(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBoolean("is_archived"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findById): " + DbConnection.friendlyMessage(e));
        }

        return null;
    }
}
