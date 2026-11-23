package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.Song;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepositoryImpl implements SongRepository {
    private final DbConnection dbConnection;

    public SongRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Song> findAllActive() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.id, s.title, s.genre, s.length, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 0 " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("length"),
                        rs.getInt("album_id"),
                        rs.getString("album_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + e.getMessage());
        }
        return songs;
    }

    @Override
    public List<Song> findAllArchived() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.id, s.title, s.genre, s.length, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 1 " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("length"),
                        rs.getInt("album_id"),
                        rs.getString("album_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + e.getMessage());
        }
        return songs;
    }

    @Override
    public boolean save(Song song) {
        String query = "INSERT INTO songs (title, genre, length, album_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getGenre());
            prep.setString(3, song.getLength());
            prep.setInt(4, song.getAlbumId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Song song) {
        String query = "UPDATE songs SET title = ?, genre = ?, length = ?, album_id = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getGenre());
            prep.setString(3, song.getLength());
            prep.setInt(4, song.getAlbumId());
            prep.setInt(5, song.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM songs WHERE id = ?";

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
        String query = "UPDATE songs SET is_archived = 1 WHERE id = ?";

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
        String query = "UPDATE songs SET is_archived = 0 WHERE id = ?";

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
    public List<Song> searchByTitle(String keyword) {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.id, s.title, s.genre, s.length, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 0 AND s.title LIKE ? " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    songs.add(new Song(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("length"),
                            rs.getInt("album_id"),
                            rs.getString("album_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByTitle): " + e.getMessage());
        }
        return songs;
    }
}
