package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepositoryImpl implements PlaylistRepository {
    private final DbConnection dbConnection;

    public PlaylistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Playlist> findAllActive() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.user_id, u.username " +
                "FROM playlists p " +
                "JOIN users u ON p.user_id = u.id " +
                "WHERE p.is_archived = 0 " +
                "ORDER BY p.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                playlists.add(new Playlist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("user_id"),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + e.getMessage());
        }
        return playlists;
    }

    @Override
    public List<Playlist> findAllArchived() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.user_id, u.username " +
                "FROM playlists p " +
                "JOIN users u ON p.user_id = u.id " +
                "WHERE p.is_archived = 1 " +
                "ORDER BY p.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                playlists.add(new Playlist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("user_id"),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + e.getMessage());
        }
        return playlists;
    }

    @Override
    public boolean save(Playlist playlist) {
        String query = "INSERT INTO playlists (name, user_id) VALUES (?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());
            prep.setInt(2, playlist.getUserId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Playlist playlist) {
        String query = "UPDATE playlists SET name = ?, user_id = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());
            prep.setInt(2, playlist.getUserId());
            prep.setInt(3, playlist.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM playlists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (deleteById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean archiveById(int id) {
        String query = "UPDATE playlists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (archiveById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean restoreById(int id) {
        String query = "UPDATE playlists SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (restoreById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Playlist> searchByName(String keyword) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.user_id, u.username " +
                "FROM playlists p " +
                "JOIN users u ON p.user_id = u.id " +
                "WHERE p.is_archived = 0 AND p.name LIKE ? " +
                "ORDER BY p.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    playlists.add(new Playlist(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("user_id"),
                            rs.getString("username")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByName): " + e.getMessage());
        }
        return playlists;
    }
}
