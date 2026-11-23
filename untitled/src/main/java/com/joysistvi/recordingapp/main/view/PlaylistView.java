package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.Playlist;
import com.joysistvi.recordingapp.main.service.PlaylistService;
import java.util.List;
import java.util.Scanner;

public class PlaylistView {
    private final PlaylistService playlistService;
    private final Scanner scanner;

    public PlaylistView(PlaylistService playlistService, Scanner scanner) {
        this.playlistService = playlistService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
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
        System.out.println(header);
        System.out.printf("%-4s %-28s %-18s%n", "ID", "Playlist Name", "Owner");

        if (playlists == null || playlists.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Playlist playlist : playlists) {
            System.out.printf("%-4d %-28s %-18s%n",
                    playlist.getId(),
                    playlist.getName(),
                    playlist.getUsername());
        }
    }

    private int promptForInt(String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
