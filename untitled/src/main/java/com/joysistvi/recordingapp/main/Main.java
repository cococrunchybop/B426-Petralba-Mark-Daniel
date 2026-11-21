package com.joysistvi.recordingapp.main;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Main {

    static String url = "jdbc:mysql://localhost:3306/recording_studio_db";
    static String username = "root";
    static String password = "root@123";

    public static void main(String[] args) {

        createAlbum("   ", 1990, 1);
        createAlbum("Coco", 1800, 1);
        createAlbum("Coco", 1990, 0);
        updateAlbum(0, "Coco", 1990, 1);
        deleteAlbum(-1);
        archiveAlbum(0);
        restoreAlbum(0);
        searchAlbum("   ");

        createArtist(null);
        updateArtist(0, "IV of Spades");
        deleteArtist(-5);
        archiveArtist(0);
        restoreArtist(0);
        searchArtist("");

        createUser("", "yuno123", "yuno@gmail.com");
        createUser("yuno", "yuno123", "yunogmail");
        createUser("yuno", "123", "yuno@gmail.com");
        loginUser("", "yuno123");
        loginUser("yuno", "");

        viewAllAlbums();
        createAlbum("Coco", 1990, 1);
        updateAlbum(18, "Coco (Remastered)", 1995, 1);
        deleteAlbum(18);

        viewAllArtists();
        createArtist("IV of Spades");
        updateArtist(18, "IV Of Spades");
        deleteArtist(18);

        searchArtist("Ben");
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void viewAllAlbums() {
        String query = "SELECT albums.id, albums.name, albums.year, artists.name AS artist_name " +
                "FROM albums " +
                "JOIN artists " +
                "ON albums.artist_id = artists.id";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n----- Albums -----");
            System.out.printf("%-4s %-20s %-8s %-20s%n", "ID", "Name", "Year", "Artist");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                String artistName = resultSet.getString("artist_name");
                System.out.printf("%-4d %-20s %-8d %-20s%n", id, name, year, artistName);
            }
        } catch (SQLException e) {
            System.out.println("Read Albums: " + e.getMessage());
        }
    }

    public static void createAlbum(String name, int year, int artistId) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Album name is required.");
            return;
        }

        if (year < 1900 || year > 2100) {
            System.out.println("\nValidation Error: Year must be between 1900 and 2100.");
            return;
        }

        if (artistId <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "INSERT INTO albums (name, year, artist_id) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);

            preparedStatement.executeUpdate();

            System.out.println("Album: " + name.trim() + " created successfully!");
            viewAllAlbums();
        } catch (SQLException e) {
            System.out.println("Create Album Error: " + e.getMessage());
        }
    }

    public static void updateAlbum(int id, String name, int year, int artistId) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Album name is required.");
            return;
        }

        if (year < 1900 || year > 2100) {
            System.out.println("\nValidation Error: Year must be between 1900 and 2100.");
            return;
        }

        if (artistId <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " updated successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nUpdate Album Error: " + e.getMessage());
        }
    }

    public static void deleteAlbum(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " deleted successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            // Also shows the MySQL constraint message if a song is still linked (ON DELETE RESTRICT)
            System.out.println("\nDelete Album Error: " + e.getMessage());
        }
    }

    public static void searchAlbum(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("\nValidation Error: Search keyword is required.");
            return;
        }

        String query = "SELECT albums.id, albums.name, albums.year, artists.name AS artist_name " +
                "FROM albums " +
                "JOIN artists ON albums.artist_id = artists.id " +
                "WHERE albums.is_archived = 0 AND albums.name LIKE ? " +
                "ORDER BY albums.id ASC";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            String cleanKeyword = keyword.trim();
            prep.setString(1, "%" + cleanKeyword + "%");

            try (ResultSet res = prep.executeQuery()) {
                System.out.println("\n----- Search Albums (Keyword: '" + cleanKeyword + "') -----");
                System.out.printf("%-4s %-22s %-8s %-20s%n", "ID", "Name", "Year", "Artist");

                boolean found = false;
                while (res.next()) {
                    found = true;
                    System.out.printf("%-4d %-22s %-8d %-20s%n",
                            res.getInt("id"),
                            res.getString("name"),
                            res.getInt("year"),
                            res.getString("artist_name"));
                }

                if (!found) {
                    System.out.println("No matching active albums found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSearch Album Error: " + e.getMessage());
        }
    }

    public static void archiveAlbum(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " archived successfully!");
            } else {
                System.out.println("\nNo album found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("\nArchive Album Error: " + e.getMessage());
        }
    }

    public static void restoreAlbum(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET is_archived = 0 WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " restored successfully!");
            } else {
                System.out.println("\nNo album found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("\nRestore Album Error: " + e.getMessage());
        }
    }

    public static void viewAllArtists() {
        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n----- Artists -----");
            System.out.printf("%-4s %-20s%n", "ID", "Artist Name");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%-4d %-20s%n", id, name);
            }
        } catch (SQLException e) {
            System.out.println("Read Artists Error: " + e.getMessage());
        }
    }

    public static void createArtist(String name) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return;
        }

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());

            preparedStatement.executeUpdate();

            System.out.println("\nArtist: " + name.trim() + " created successfully!");
            viewAllArtists();
        } catch (SQLException e) {
            System.out.println("Create Artist Error: " + e.getMessage());
        }
    }

    public static void updateArtist(int id, String name) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return;
        }

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " updated successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nUpdate Artist Error: " + e.getMessage());
        }
    }

    public static void deleteArtist(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " deleted successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nDelete Artist Error: " + e.getMessage());
        }
    }

    public static void archiveArtist(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " archived successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nArchive Artist Error: " + e.getMessage());
        }
    }

    public static void restoreArtist(int id) {

        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " restored successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nRestore Artist Error: " + e.getMessage());
        }
    }

    public static void searchArtist(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("\nValidation Error: Search keyword is required.");
            return;
        }

        String query = "SELECT id, name FROM artists " +
                "WHERE is_archived = 0 AND name LIKE ? " +
                "ORDER BY id ASC";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            String cleanKeyword = keyword.trim();
            prep.setString(1, "%" + cleanKeyword + "%");

            try (ResultSet res = prep.executeQuery()) {
                System.out.println("\n----- Search Artists (Keyword: '" + cleanKeyword + "') -----");
                System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

                boolean found = false;
                while (res.next()) {
                    found = true;
                    System.out.printf("%-4d %-25s%n",
                            res.getInt("id"),
                            res.getString("name"));
                }

                if (!found) {
                    System.out.println("No matching active artists found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSearch Artist Error: " + e.getMessage());
        }
    }

    public static void createUser(String uname, String rawPassword, String email) {

        if (uname == null || uname.trim().isEmpty()) {
            System.out.println("\nValidation Error: Username is required.");
            return;
        }

        if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            System.out.println("\nValidation Error: Valid email is required.");
            return;
        }

        if (rawPassword == null || rawPassword.length() < 6) {
            System.out.println("\nValidation Error: Password must be at least 6 characters.");
            return;
        }

        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, 'USER')";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, uname.trim());
            prep.setString(2, hashedPassword);
            prep.setString(3, email.trim());

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nUser '" + uname.trim() + "' created successfully!");
            }
        } catch (SQLException e) {
            System.out.println("\nCreate User Error: " + e.getMessage());
        }
    }

    public static void loginUser(String uname, String candidatePassword) {

        if (uname == null || uname.trim().isEmpty()) {
            System.out.println("\nValidation Error: Username is required to log in.");
            return;
        }

        if (candidatePassword == null || candidatePassword.isEmpty()) {
            System.out.println("\nValidation Error: Password is required to log in.");
            return;
        }

        String query = "SELECT username, password, role FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, uname.trim());

            try (ResultSet res = prep.executeQuery()) {
                if (res.next()) {
                    String storedHash = res.getString("password");

                    if (BCrypt.checkpw(candidatePassword, storedHash)) {
                        String username = res.getString("username");
                        String role = res.getString("role");
                        System.out.println("\nLogin successful! Welcome, " + username + " (Role: " + role + ")");
                    } else {
                        System.out.println("\nLogin failed: Invalid username or password.");
                    }
                } else {
                    System.out.println("\nLogin failed: Invalid username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nLogin Error: " + e.getMessage());
        }
    }
}