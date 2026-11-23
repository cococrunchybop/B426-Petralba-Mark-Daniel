package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final DbConnection dbConnection;

    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> findAllActive() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, password, email, role FROM users WHERE is_archived = 0 ORDER BY id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> findAllArchived() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, password, email, role FROM users WHERE is_archived = 1 ORDER BY id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + e.getMessage());
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT id, username, password, email, role FROM users WHERE username = ? AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);

            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findByUsername): " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean save(User user) {
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getEmail());
            prep.setString(4, user.getRole());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE users SET username = ?, email = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getEmail());
            prep.setInt(3, user.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM users WHERE id = ?";

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
        String query = "UPDATE users SET is_archived = 1 WHERE id = ?";

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
        String query = "UPDATE users SET is_archived = 0 WHERE id = ?";

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
    public List<User> searchByUsername(String keyword) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, password, email, role FROM users WHERE is_archived = 0 AND username LIKE ? ORDER BY id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("role")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByUsername): " + e.getMessage());
        }
        return users;
    }
}