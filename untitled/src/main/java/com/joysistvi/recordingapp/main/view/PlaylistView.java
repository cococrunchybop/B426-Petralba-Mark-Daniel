package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.Playlist;
import com.joysistvi.recordingapp.main.model.Song;
import com.joysistvi.recordingapp.main.service.PlaylistService;
import com.joysistvi.recordingapp.main.service.SongService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PlaylistView {
    private final PlaylistService playlistService;
    private final SongService songService;
    private final Scanner scanner;

    public PlaylistView(PlaylistService playlistService, SongService songService, Scanner scanner) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.scanner = scanner;
    }

    // ===================== ADMIN (all playlists) =====================

    public void handleMenu() {
        while (true) {
            ConsoleUtil.clearScreen();
            System.out.println("\n=== MANAGE PLAYLISTS ===");
            System.out.println("1. View All Playlists");
            System.out.println("2. Create Playlist");
            System.out.println("3. Update Playlist");
            System.out.println("4. Search Playlist");
            System.out.println("5. Archive Playlist");
            System.out.println("6. Restore Playlist");
            System.out.println("7. Delete Playlist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> displayAllPlaylists();
                    case 2 -> promptCreatePlaylist();
                    case 3 -> promptUpdatePlaylist();
                    case 4 -> promptSearchPlaylists();
                    case 5 -> promptArchivePlaylist();
                    case 6 -> promptRestorePlaylist();
                    case 7 -> promptDeletePlaylist();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
            }
            ConsoleUtil.pause(scanner);
        }
    }

    public void displayAllPlaylists() {
        printPlaylistTable("\n----- Active Playlists -----", playlistService.getAllActivePlaylists(), "No active playlists found.");
    }

    public void promptCreatePlaylist() {
        System.out.println("\n=== CREATE Playlist ===");
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        int userId = promptForInt("Enter user ID (owner): ");

        boolean created = playlistService.createPlaylist(name, userId);
        if (created) {
            System.out.println("Playlist '" + name.trim() + "' created successfully!");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

    public void promptUpdatePlaylist() {
        System.out.println("\n=== UPDATE Playlist ===");
        displayAllPlaylists();
        int id = promptForInt("Enter playlist ID to update: ");
        System.out.print("Enter new playlist name: ");
        String name = scanner.nextLine();
        int userId = promptForInt("Enter user ID (owner): ");

        boolean updated = playlistService.updatePlaylist(id, name, userId);
        if (updated) {
            System.out.println("Playlist ID " + id + " updated successfully!");
        } else {
            System.out.println("No playlist updated (check ID or input).");
        }
    }

    public void promptDeletePlaylist() {
        System.out.println("\n=== DELETE Playlist ===");
        displayAllPlaylists();
        int id = promptForInt("Enter playlist ID to delete: ");

        boolean deleted = playlistService.deletePlaylist(id);
        if (deleted) {
            System.out.println("Playlist ID " + id + " deleted successfully!");
        } else {
            System.out.println("No playlist deleted (check ID or input).");
        }
    }

    public void promptArchivePlaylist() {
        System.out.println("\n=== ARCHIVE Playlist ===");
        displayAllPlaylists();
        int id = promptForInt("Enter playlist ID to archive: ");

        boolean archived = playlistService.archivePlaylist(id);
        if (archived) {
            System.out.println("Playlist ID " + id + " archived successfully!");
        } else {
            System.out.println("No playlist archived (check ID or input).");
        }
    }

    public void promptRestorePlaylist() {
        System.out.println("\n=== RESTORE Playlist ===");
        List<Playlist> archivedPlaylists = playlistService.getAllArchivedPlaylists();
        printPlaylistTable("\n----- Archived Playlists -----", archivedPlaylists, "No archived playlists found.");

        if (archivedPlaylists == null || archivedPlaylists.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter playlist ID to restore: ");
        boolean restored = playlistService.restorePlaylist(id);
        if (restored) {
            System.out.println("Playlist ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived playlist restored (check ID or input).");
        }
    }

    public void promptSearchPlaylists() {
        System.out.println("\n=== SEARCH Playlists ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Playlist> matches = playlistService.searchPlaylists(keyword);
        printPlaylistTable("\n----- Search Playlists (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active playlists found.");
    }

    private void printPlaylistTable(String header, List<Playlist> playlists, String emptyMessage) {
        String border = "+------+------------------------------+--------------------+";

        System.out.println(header);
        System.out.println(border);
        System.out.printf("| %-4s | %-28s | %-18s |%n", "ID", "Playlist Name", "Owner");
        System.out.println(border);

        if (playlists == null || playlists.isEmpty()) {
            String message = (emptyMessage != null && !emptyMessage.isEmpty())
                    ? emptyMessage
                    : "No playlists found.";
            System.out.printf("| %-56s |%n", truncate(message, 56));
            System.out.println(border);
            return;
        }

        for (Playlist playlist : playlists) {
            System.out.printf("| %-4d | %-28s | %-18s |%n",
                    playlist.getId(),
                    truncate(playlist.getName(), 28),
                    truncate(playlist.getUsername(), 18));
        }

        System.out.println(border);
    }

    // ===================== USER (own playlist) =====================

    // USER menu: view / create / delete their own playlist
    public void handleMenu(int userId) {
        while (true) {
            ConsoleUtil.clearScreen();
            System.out.println("\n=== MANAGE PLAYLIST ===");
            System.out.println("1. View Playlist");
            System.out.println("2. Create Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> handleViewUserPlaylists(userId);
                    case 2 -> promptCreatePlaylist(userId);
                    case 3 -> promptDeleteUserPlaylist(userId);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
            }
            ConsoleUtil.pause(scanner);
        }
    }

    public void promptCreatePlaylist(int userId) {
        System.out.println("\n--- Create Playlist ---");
        if (!playlistService.getPlaylistsByUser(userId).isEmpty()) {
            System.out.println("You already have a playlist. Only one playlist is allowed per user.");
            return;
        }

        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();

        if (playlistService.createPlaylist(name, userId)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

    public void handleViewUserPlaylists(int userId) {
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("\nNo playlist found.");
            return;
        }

        for (Playlist playlist : userPlaylists) {
            System.out.println("\nPlaylist: " + playlist.getName());
            List<Song> songs = playlistService.getSongsInPlaylist(playlist.getId());
            printSongListTable("----- Current Songs in Playlist -----", songs);
        }
    }

    public void promptDeleteUserPlaylist(int userId) {
        System.out.println("\n--- Delete Playlist ---");
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("No playlist found to delete.");
            return;
        }

        Playlist playlist = userPlaylists.get(0);
        if (playlistService.deletePlaylist(playlist.getId())) {
            System.out.println("Playlist \"" + playlist.getName() + "\" deleted successfully.");
        } else {
            System.out.println("Failed to delete playlist.");
        }
    }

    // USER menu: add / remove songs in their own playlist
    public void handleManageSongsInPlaylist(int userId) {
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("\nNo playlist found for this user. Please create a playlist first.");
            return;
        }

        Playlist playlist = userPlaylists.get(0);

        while (true) {
            ConsoleUtil.clearScreen();
            System.out.println("\n=== MANAGE SONGS (" + playlist.getName() + ") ===");
            System.out.println("1. Add Song to Playlist");
            System.out.println("2. Remove Song from Playlist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> promptAddSongToPlaylist(playlist);
                    case 2 -> promptRemoveSongFromPlaylist(playlist);
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
            }
            ConsoleUtil.pause(scanner);
        }
    }

    private void promptAddSongToPlaylist(Playlist playlist) {
        printSongListTable("----- Available Songs -----", songService.getAllActiveSongs());
        int songId = promptForInt("Enter song ID to add: ");

        for (Song existing : playlistService.getSongsInPlaylist(playlist.getId())) {
            if (existing.getId() == songId) {
                System.out.println("That song is already in your playlist.");
                return;
            }
        }

        if (playlistService.addSongToPlaylist(playlist.getId(), songId)) {
            System.out.println("Song added to playlist.");
        } else {
            System.out.println("Failed to add song to playlist.");
        }
    }

    private void promptRemoveSongFromPlaylist(Playlist playlist) {
        printSongListTable("----- Current Songs in Playlist -----", playlistService.getSongsInPlaylist(playlist.getId()));
        int songId = promptForInt("Enter song ID to remove: ");

        if (playlistService.removeSongFromPlaylist(playlist.getId(), songId)) {
            System.out.println("Song removed from playlist.");
        } else {
            System.out.println("Failed to remove song from playlist (is it in your playlist?).");
        }
    }

    private void printSongListTable(String header, List<Song> songs) {
        String border = "+-------+------------------------+-----------------+----------+----------------------+";

        System.out.println("\n" + header);
        System.out.println(border);
        System.out.printf("| %-5s | %-22s | %-15s | %-8s | %-20s |%n",
                "ID", "Title", "Genre", "Length", "Album");
        System.out.println(border);

        if (songs == null || songs.isEmpty()) {
            System.out.printf("| %-82s |%n", "No songs found.");
            System.out.println(border);
            return;
        }

        for (Song s : songs) {
            String title = s.getTitle() != null ? s.getTitle() : "";
            String genre = s.getGenre() != null ? s.getGenre() : "";
            String length = s.getLength() != null ? s.getLength() : "";
            String album = s.getAlbumName() != null ? s.getAlbumName() : "";

            System.out.printf("| %-5d | %-22s | %-15s | %-8s | %-20s |%n",
                    s.getId(),
                    truncate(title, 22),
                    truncate(genre, 15),
                    truncate(length, 8),
                    truncate(album, 20));
        }

        System.out.println(border);
    }

    private String truncate(String text, int maxLength) {
        return ConsoleUtil.truncate(text, maxLength);
    }

    private int promptForInt(String promptMessage) {
        return ConsoleUtil.promptForInt(scanner, promptMessage);
    }
}
