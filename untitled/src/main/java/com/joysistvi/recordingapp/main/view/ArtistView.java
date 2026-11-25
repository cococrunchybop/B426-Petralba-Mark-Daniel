package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.Artist;
import com.joysistvi.recordingapp.main.service.ArtistService;

import java.util.List;
import java.util.NoSuchElementException;
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
            ConsoleUtil.clearScreen();
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
            try {
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
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
            }
            ConsoleUtil.pause(scanner);
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

        Artist current = artistService.getArtistById(id);
        if (current == null) {
            System.out.println("Artist not found or invalid ID.");
            return;
        }

        System.out.print("Enter new artist name (press Enter to keep current [" + current.getName() + "]): ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            name = current.getName();
        } else {
            name = name.trim();
        }

        boolean updated = artistService.updateArtist(id, name);
        if (updated) {
            System.out.println("Artist ID " + id + " updated successfully!");
            displayAllArtists();
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
        String border = "+------+---------------------------+";

        System.out.println(header);
        System.out.println(border);
        System.out.printf("| %-4s | %-25s |%n", "ID", "Artist Name");
        System.out.println(border);

        if (artists == null || artists.isEmpty()) {
            String message = (emptyMessage != null && !emptyMessage.isEmpty())
                    ? emptyMessage
                    : "No artists found.";
            System.out.printf("| %-32s |%n", truncate(message, 32));
            System.out.println(border);
            return;
        }

        for (Artist artist : artists) {
            String name = artist.getName() != null ? artist.getName() : "";
            System.out.printf("| %-4d | %-25s |%n", artist.getId(), truncate(name, 25));
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
