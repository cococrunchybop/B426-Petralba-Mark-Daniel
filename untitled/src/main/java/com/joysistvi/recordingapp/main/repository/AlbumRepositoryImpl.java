package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.Album;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository {
    private final DbConnection dbConnection;

    public AlbumRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Album> findAllActive() {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT a.id, a.name, a.year, a.artist_id, ar.name AS artist_name " +
                "FROM albums a " +
                "JOIN artists ar ON a.artist_id = ar.id " +
                "WHERE a.is_archived = 0 " +
                "ORDER BY a.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                albums.add(new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id"),
                        rs.getString("artist_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + DbConnection.friendlyMessage(e));
        }
        return albums;
    }

    @Override
    public List<Album> findAllArchived() {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT a.id, a.name, a.year, a.artist_id, ar.name AS artist_name " +
                "FROM albums a " +
                "JOIN artists ar ON a.artist_id = ar.id " +
                "WHERE a.is_archived = 1 " +
                "ORDER BY a.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                albums.add(new Album(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getInt("artist_id"),
                        rs.getString("artist_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + DbConnection.friendlyMessage(e));
        }
        return albums;
    }

    @Override
    public boolean save(Album album) {
        String query = "INSERT INTO albums (name, year, artist_id) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, album.getName());
            prep.setInt(2, album.getYear());
            prep.setInt(3, album.getArtistId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean update(Album album) {
        String query = "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, album.getName());
            prep.setInt(2, album.getYear());
            prep.setInt(3, album.getArtistId());
            prep.setInt(4, album.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (deleteById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean archiveById(int id) {
        String query = "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (archiveById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public boolean restoreById(int id) {
        String query = "UPDATE albums SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (restoreById): " + DbConnection.friendlyMessage(e));
            return false;
        }
    }

    @Override
    public List<Album> searchByName(String keyword) {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT a.id, a.name, a.year, a.artist_id, ar.name AS artist_name " +
                "FROM albums a " +
                "JOIN artists ar ON a.artist_id = ar.id " +
                "WHERE a.is_archived = 0 AND a.name LIKE ? " +
                "ORDER BY a.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    albums.add(new Album(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("year"),
                            rs.getInt("artist_id"),
                            rs.getString("artist_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByName): " + DbConnection.friendlyMessage(e));
        }
        return albums;
    }
}