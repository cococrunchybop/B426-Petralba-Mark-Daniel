package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.Song;
import com.joysistvi.recordingapp.main.service.SongService;
import java.util.List;
import java.util.Scanner;

public class SongView {
    private final SongService songService;
    private final Scanner scanner;

    public SongView(SongService songService, Scanner scanner) {
        this.songService = songService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== MANAGE SONGS ===");
            System.out.println("1. View All Songs");
            System.out.println("2. Create Song");
            System.out.println("3. Update Song");
            System.out.println("4. Search Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Delete Song");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1 -> displayAllSongs();
                case 2 -> promptCreateSong();
                case 3 -> promptUpdateSong();
                case 4 -> promptSearchSongs();
                case 5 -> promptArchiveSong();
                case 6 -> promptRestoreSong();
                case 7 -> promptDeleteSong();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayAllSongs() {
        printSongTable("\n----- Active Songs -----", songService.getAllActiveSongs(), "No active songs found.");
    }

    public void promptCreateSong() {
        System.out.println("\n=== CREATE Song ===");
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter length (m:ss, e.g. 3:45): ");
        String length = scanner.nextLine();
        int albumId = promptForInt("Enter album ID: ");

        boolean created = songService.createSong(title, genre, length, albumId);
        if (created) {
            System.out.println("Song '" + title.trim() + "' created successfully!");
        } else {
            System.out.println("Failed to create song.");
        }
    }

    public void promptUpdateSong() {
        System.out.println("\n=== UPDATE Song ===");
        displayAllSongs();
        int id = promptForInt("Enter song ID to update: ");
        System.out.print("Enter new song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new length (m:ss, e.g. 3:45): ");
        String length = scanner.nextLine();
        int albumId = promptForInt("Enter album ID: ");

        boolean updated = songService.updateSong(id, title, genre, length, albumId);
        if (updated) {
            System.out.println("Song ID " + id + " updated successfully!");
        } else {
            System.out.println("No song updated (check ID or input).");
        }
    }

    public void promptDeleteSong() {
        System.out.println("\n=== DELETE Song ===");
        displayAllSongs();
        int id = promptForInt("Enter song ID to delete: ");

        boolean deleted = songService.deleteSong(id);
        if (deleted) {
            System.out.println("Song ID " + id + " deleted successfully!");
        } else {
            System.out.println("No song deleted (check ID or input).");
        }
    }

    public void promptArchiveSong() {
        System.out.println("\n=== ARCHIVE Song ===");
        displayAllSongs();
        int id = promptForInt("Enter song ID to archive: ");

        boolean archived = songService.archiveSong(id);
        if (archived) {
            System.out.println("Song ID " + id + " archived successfully!");
        } else {
            System.out.println("No song archived (check ID or input).");
        }
    }

    public void promptRestoreSong() {
        System.out.println("\n=== RESTORE Song ===");
        List<Song> archivedSongs = songService.getAllArchivedSongs();
        printSongTable("\n----- Archived Songs -----", archivedSongs, "No archived songs found.");

        if (archivedSongs == null || archivedSongs.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter song ID to restore: ");
        boolean restored = songService.restoreSong(id);
        if (restored) {
            System.out.println("Song ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived song restored (check ID or input).");
        }
    }

    public void promptSearchSongs() {
        System.out.println("\n=== SEARCH Songs ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Song> matches = songService.searchSongs(keyword);
        printSongTable("\n----- Search Songs (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active songs found.");
    }

    private void printSongTable(String header, List<Song> songs, String emptyMessage) {
        System.out.println(header);
        System.out.printf("%-4s %-28s %-10s %-8s %-22s%n", "ID", "Title", "Genre", "Length", "Album");

        if (songs == null || songs.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Song song : songs) {
            System.out.printf("%-4d %-28s %-10s %-8s %-22s%n",
                    song.getId(),
                    song.getTitle(),
                    song.getGenre(),
                    song.getLength(),
                    song.getAlbumName());
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
