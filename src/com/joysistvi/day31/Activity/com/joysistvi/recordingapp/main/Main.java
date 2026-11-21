package com.joysistvi.recordingapp.main;

import java.sql.*;

public class Main {

    static String url = "jdbc:mysql://localhost:3306/recording_studio_db";
    static String username = "root";
    static String password = "root@123";

    public static void main(String[] args) {

        viewAllAlbums();
        createAlbum("Coco", 1990, 1);
        updateAlbum(14, "Coco (Remastered)", 1995, 1);
        deleteAlbum(14);

        viewAllArtists();
        createArtist("IV of Spades");
        updateArtist(14, "IV Of Spades");
        deleteArtist(14);
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
        String query = "INSERT INTO albums (name, year, artist_id) VALUES (?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);

            preparedStatement.executeUpdate();

            System.out.println("Album: " + name + " created successfully!");
            viewAllAlbums();
        } catch (SQLException e) {
            System.out.println("Create Album: " + e.getMessage());
        }
    }


    public static void updateAlbum(int id, String name, int year, int artistId) {
        String query = "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Album ID " + id + " updated successfully!");
                viewAllAlbums();
            } else {
                System.out.println("No album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Update Album: " + e.getMessage());
        }
    }

    public static void deleteAlbum(int id) {
        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Album ID " + id + " deleted successfully!");
                viewAllAlbums();
            } else {
                System.out.println("No album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            // Also shows the MySQL constraint message if a song is still linked (ON DELETE RESTRICT)
            System.out.println("Delete Album: " + e.getMessage());
        }
    }


    public static void viewAllArtists() {
        String query = "SELECT id, name FROM artists";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n----- Artists -----");
            System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%-4d %-25s%n", id, name);
            }
        } catch (SQLException e) {
            System.out.println("Read Artists: " + e.getMessage());
        }
    }


    public static void createArtist(String name) {
        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

            System.out.println("Artist: " + name + " created successfully!");
            viewAllArtists();
        } catch (SQLException e) {
            System.out.println("Create Artist: " + e.getMessage());
        }
    }


    public static void updateArtist(int id, String name) {
        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Artist ID " + id + " updated successfully!");
                viewAllArtists();
            } else {
                System.out.println("No artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Update Artist: " + e.getMessage());
        }
    }


    public static void deleteArtist(int id) {
        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Artist ID " + id + " deleted successfully!");
                viewAllArtists();
            } else {
                System.out.println("No artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("Delete Artist: " + e.getMessage());
        }
    }
}