package com.joysistvi.recordingapp.main;

import com.joysistvi.recordingapp.main.config.DbConnection;
import com.joysistvi.recordingapp.main.model.User;
import com.joysistvi.recordingapp.main.repository.*;
import com.joysistvi.recordingapp.main.service.*;
import com.joysistvi.recordingapp.main.view.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

// Role-Based Access Control (RBAC)  ADMIN vs. USER

// Entry Point
public class App {

    private final Scanner scanner;
    private final UserView userView;
    private final ArtistView artistView;
    private final AlbumView albumView;
    private final SongView songView;
    private final PlaylistView playlistView;

    public App(Scanner scanner, UserView userView, ArtistView artistView, AlbumView albumView,
               SongView songView, PlaylistView playlistView) {
        this.scanner = scanner;
        this.userView = userView;
        this.artistView = artistView;
        this.albumView = albumView;
        this.songView = songView;
        this.playlistView = playlistView;
    }

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Scanner scanner = new Scanner(System.in);

        // Wiring (bottom-up): Repository -> Service -> View
        // 1. Wire Artist
        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistView artistView = new ArtistView(artistService, scanner);

        // 2. Wire Album
        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumView albumView = new AlbumView(albumService, scanner);

        // 3. Wire User
        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserView userView = new UserView(userService, scanner);

        // 4. Wire Song
        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
        SongService songService = new SongServiceImpl(songRepository);
        SongView songView = new SongView(songService, scanner);

        // 5. Wire Playlist (also needs SongService to list songs that can be added)
        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
        PlaylistService playlistService = new PlaylistServiceImpl(playlistRepository);
        PlaylistView playlistView = new PlaylistView(playlistService, songService, scanner);

        App app = new App(scanner, userView, artistView, albumView, songView, playlistView);
        app.run();

        scanner.close();
    }

    public void run() {

        try {
            // Logout only ends the dashboard; the loop returns to the Login / Register / Exit menu.
            // The app stops only when the user chooses Exit (showAuthMenuAndAuthenticate returns null).
            while (true) {
                User currentUser = showAuthMenuAndAuthenticate();
                if (currentUser == null) {
                    System.out.println("\nExiting Recording Studio App. Goodbye!");
                    break;
                }

                System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")!");
                pause();

                // Role-based routing
                if ("ADMIN".equalsIgnoreCase(currentUser.getRole())) {
                    adminDashboard();
                } else {
                    userDashboard(currentUser);
                }

                System.out.println("\nLogged out " + currentUser.getUsername() + ".");
                pause();
            }
        } catch (NoSuchElementException e) {
            // input stream was closed (e.g. Ctrl+D / Ctrl+Z) - leave quietly instead of crashing
            System.out.println("\n\nInput closed. Exiting Recording Studio App. Goodbye!");
        }
    }

    // Authorization vs Authentication
    private void adminDashboard() {
        while (true) {
            clearScreen();
            System.out.println("\n=== ADMIN DASHBOARD ===");
            System.out.println("1. Song Management");
            System.out.println("2. Artist Management");
            System.out.println("3. Album Management");
            System.out.println("4. Users Management");
            System.out.println("5. Playlist Management");
            System.out.println("0. Logout");

            int choice = promptForInt("Choice: ");
            try {
                switch (choice) {
                    case 1 -> songView.handleMenu();
                    case 2 -> artistView.handleMenu();
                    case 3 -> albumView.handleMenu();
                    case 4 -> userView.handleMenu();
                    case 5 -> playlistView.handleMenu();
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pause();
                    }
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
                pause();
            }
        }
    }

    private void userDashboard(User currentUser) {
        while (true) {
            clearScreen();
            System.out.println("\n=== USER DASHBOARD ===");
            System.out.println("1. Browse Artists, Albums, Songs");
            System.out.println("2. Search Songs");
            System.out.println("3. Manage Playlist");
            System.out.println("4. Add / Remove Songs in Playlist");
            System.out.println("0. Logout");

            int choice = promptForInt("Choice: ");

            try {
                switch (choice) {
                    case 1 -> browseCatalog();
                    case 2 -> {
                        songView.promptSearchSongs();
                        pause();
                    }
                    case 3 -> playlistView.handleMenu(currentUser.getId());
                    case 4 -> playlistView.handleManageSongsInPlaylist(currentUser.getId());
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice. Please try again.");
                        pause();
                    }
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
                pause();
            }
        }
    }

    private void browseCatalog() {
        while (true) {
            clearScreen();
            System.out.println("\n=== BROWSE CATALOG ===");
            System.out.println("1. View Artists");
            System.out.println("2. View Albums");
            System.out.println("3. View Songs");
            System.out.println("0. Back");

            int choice = promptForInt("Choice: ");

            try {
                switch (choice) {
                    case 1 -> artistView.displayAllArtists();
                    case 2 -> albumView.displayAllAlbums();
                    case 3 -> songView.displayAllSongs();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid Choice: Please try again.");
                }
            } catch (NoSuchElementException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println("\nSomething went wrong: " + e.getMessage());
            }
            pause();
        }
    }

    private User showAuthMenuAndAuthenticate() {
        while (true) {
            clearScreen();
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
                    pause();
                    break;

                case 2:
                    userView.promptCreateUser();
                    pause();
                    break;
                case 0:
                    return null;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pause();
                    break;
            }
        }
    }


    private int promptForInt(String promptMessage) {
        return ConsoleUtil.promptForInt(scanner, promptMessage);
    }

    private void pause() {
        ConsoleUtil.pause(scanner);
    }

    // Works in real terminals and in IntelliJ's Run console (see ConsoleUtil.clearScreen).
    private void clearScreen() {
        ConsoleUtil.clearScreen();
    }
}


/*
===============================================================================
                             Build &  Wiring Order (Bottom-Up)
===============================================================================

    Model
    │
    ▼
    Repository
     │
     ▼
    Service
     │
     ▼
    View
     │
     ▼
    Main Application (App)

===============================================================================
                    DATA FLOW (Top-Down)
===============================================================================

REQUEST FLOW (Save / Insert / Update / Delete)

User/App
  ↓
View
  ↓
Service
  ↓
Repository (DAO)
  ↓
Database


RESPONSE FLOW (Retrieve / Search)

Database
  ↓
Repository (DAO)
  ↓
Service
  ↓
View
  ↓
User


Dependency Wiring:
Repository --> Service --> View --> main()

Example:
ArtistRepositoryImpl
        │
        ▼
ArtistServiceImpl
        │
        ▼
ArtistView
        │
        ▼
App.main()

Dependency Flow
View → Service → Repository → Database

Data Flow
User → View → Service → Repository → Database
Database → Repository → Service → View → User
 */
