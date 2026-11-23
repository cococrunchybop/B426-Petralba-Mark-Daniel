package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.Artist;
import com.joysistvi.recordingapp.main.service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistService artistService;
    private final Scanner scanner;

    public ArtistView(ArtistService artistService, Scanner scanner) {
        this.artistService = artistService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== MANAGE ARTISTS ===");
            System.out.println("1. View All Artists");
            System.out.println("2. Create Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Search Artist");
            System.out.println("5. Archive Artist");
            System.out.println("6. Restore Artist");
            System.out.println("7. Delete Artist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1 -> displayAllArtists();
                case 2 -> promptCreateArtist();
                case 3 -> promptUpdateArtist();
                case 4 -> promptSearchArtists();
                case 5 -> promptArchiveArtist();
                case 6 -> promptRestoreArtist();
                case 7 -> promptDeleteArtist();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void displayAllArtists() {
        printArtistTable("\n----- Active Artists -----", artistService.getAllActiveArtists(), "No active artists found.");
    }

    public void promptCreateArtist() {
        System.out.println("\n=== CREATE Artist ===");
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        boolean created = artistService.createArtist(name);
        if (created) {
            System.out.println("Artist '" + name.trim() + "' created successfully!");
        } else {
            System.out.println("Failed to create artist.");
        }
    }

    public void promptUpdateArtist() {
        System.out.println("\n=== UPDATE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to update: ");
        System.out.print("Enter new artist name: ");
        String name = scanner.nextLine();

        boolean updated = artistService.updateArtist(id, name);
        if (updated) {
            System.out.println("Artist ID " + id + " updated successfully!");
        } else {
            System.out.println("No artist updated (check ID or input).");
        }
    }

    public void promptDeleteArtist() {
        System.out.println("\n=== DELETE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to delete: ");

        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            System.out.println("Artist ID " + id + " deleted successfully!");
        } else {
            System.out.println("No artist deleted (check ID or input).");
        }
    }

    public void promptArchiveArtist() {
        System.out.println("\n=== ARCHIVE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to archive: ");

        boolean archived = artistService.archiveArtist(id);
        if (archived) {
            System.out.println("Artist ID " + id + " archived successfully!");
        } else {
            System.out.println("No artist archived (check ID or input).");
        }
    }

    public void promptRestoreArtist() {
        System.out.println("\n=== RESTORE Artist ===");
        List<Artist> archivedArtists = artistService.getAllArchivedArtists();
        printArtistTable("\n----- Archived Artists -----", archivedArtists, "No archived artists found.");

        if (archivedArtists == null || archivedArtists.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter artist ID to restore: ");
        boolean restored = artistService.restoreArtist(id);
        if (restored) {
            System.out.println("Artist ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived artist restored (check ID or input).");
        }
    }

    public void promptSearchArtists() {
        System.out.println("\n=== SEARCH Artists ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Artist> matches = artistService.searchArtists(keyword);
        printArtistTable("\n----- Search Results (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active artists found.");
    }

    private void printArtistTable(String header, List<Artist> artists, String emptyMessage) {
        System.out.println(header);
        System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

        if (artists == null || artists.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Artist artist : artists) {
            System.out.printf("%-4d %-25s%n", artist.getId(), artist.getName());
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
