package com.joysistvi.recordingapp.main;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.User;
import com.joysistvi.recordingapp.main.repository.*;
import com.joysistvi.recordingapp.main.service.*;
import com.joysistvi.recordingapp.main.view.*;

import java.util.Scanner;

// Entry Point
public class App {

    DbConnection dbConnection = new DbConnection();
    Scanner scanner = new Scanner(System.in);
    // Wiring
    // 1. Wire Artist (Repository -> Service -> View)
    ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
    ArtistService artistService = new ArtistServiceImpl(artistRepository);
    ArtistView artistView = new ArtistView(artistService, scanner);

    // 2. Wire Album (Repository -> Service -> View)
    AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
    AlbumService albumService = new AlbumServiceImpl(albumRepository);
    AlbumView albumView = new AlbumView(albumService, scanner);

    // 3. Wire User (Repository -> Service -> View)
    UserRepository userRepository = new UserRepositoryImpl(dbConnection);
    UserService userService = new UserServiceImpl(userRepository);
    UserView userView = new UserView(userService, scanner);

    // 4. Wire Song (Repository -> Service -> View)
    SongRepository songRepository = new SongRepositoryImpl(dbConnection);
    SongService songService = new SongServiceImpl(songRepository);
    SongView songView = new SongView(songService, scanner);

    // 5. Wire Playlist (Repository -> Service -> View)
    PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
    PlaylistService playlistService = new PlaylistServiceImpl(playlistRepository);
    PlaylistView playlistView = new PlaylistView(playlistService, scanner);

    public static void main(String[] args) {
        App app = new App();
        app.run();

    }

    public void run() {

        while (true) {
            User currentUser = showAuthMenuAndAuthenticate();
            if (currentUser == null) {
                System.out.println("\nExiting Recording Studio App. Goodbye!");
                break;
            }

            // Any logged-in user enter the dashboard
            System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")!");
            dashboard();
            System.out.println("\nLogged out " + currentUser.getUsername() + ".");
        }
    }

    // Authorization vs Authentication
    private void dashboard() {
        while (true) {
            System.out.println("\n1. Song Management");
            System.out.println("2. Artist Management");
            System.out.println("3. Album Management");
            System.out.println("4. Users Management");
            System.out.println("5. Playlist Management");
            System.out.println("0. Back");

            int choice = promptForInt("Choice: ");
            switch (choice) {
                case 1 -> songView.handleMenu();
                case 2 -> artistView.handleMenu();
                case 3 -> albumView.handleMenu();
                case 4 -> userView.handleMenu();
                case 5 -> playlistView.handleMenu();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private User showAuthMenuAndAuthenticate() {

        while (true) {
            System.out.println("\n=== RECORDING STUDIO APP ===");
            System.out.println("1. Login");
            System.out.println("2. Register Account");
            System.out.println("0. Exit");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    User loggedInUser = userView.promptLogin();

                    if (loggedInUser != null) {
                        return loggedInUser;
                    }
                    break;

                case 2:
                    userView.promptCreateUser();
                    break;
                case 0:
                    return null;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
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
