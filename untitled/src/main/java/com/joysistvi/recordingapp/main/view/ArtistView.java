package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistService artistService;
    private final Scanner scanner = new Scanner(System.in);

    public ArtistView(ArtistService artistService) {
        this.artistService = artistService;
    }

    public void displayAllArtists() {
        try {
            List<Artist> artists = artistService.getAllActiveArtists();

            System.out.println("\n----- Artists -----");
            System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

            for (Artist artist : artists) {
                System.out.printf("%-4d %-25s%n", artist.getId(), artist.getName());
            }
        } catch (IllegalStateException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void promptCreateArtist() {
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        try {
            if (artistService.createArtist(name)) {
                System.out.println("\nArtist: " + name.trim() + " created successfully!");
                displayAllArtists();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // TODO: promptUpdateArtist, promptDeleteArtist, promptArchiveArtist, promptRestoreArtist, promptSearchArtists
    // TODO: menu loop
}
