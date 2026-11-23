package com.joysistvi.recordingapp.main.view;

import com.joysistvi.recordingapp.main.model.User;
import com.joysistvi.recordingapp.main.service.UserService;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserService userService;
    private final Scanner scanner;

    public UserView(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== MANAGE USERS ===");
            System.out.println("1. View All Users");
            System.out.println("2. Create / Register User");
            System.out.println("3. Update User");
            System.out.println("4. Search User");
            System.out.println("5. Archive User");
            System.out.println("6. Restore User");
            System.out.println("7. Delete User");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1 -> displayAllUsers();
                case 2 -> promptCreateUser();
                case 3 -> promptUpdateUser();
                case 4 -> promptSearchUsers();
                case 5 -> promptArchiveUser();
                case 6 -> promptRestoreUser();
                case 7 -> promptDeleteUser();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public User promptLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedIn = userService.loginUser(username, password);
        if (loggedIn != null) {
            System.out.println("Login successful! Welcome, " + loggedIn.getUsername() + " (" + loggedIn.getRole() + ").");
            return loggedIn;
        }

        System.out.println("Login failed: Invalid username or password.");
        return null;
    }

    public void promptCreateUser() {
        System.out.println("\n=== CREATE / REGISTER User ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password (min 6 chars): ");
        String password = scanner.nextLine();

        boolean created = userService.createUser(username, password, email);
        if (created) {
            System.out.println("User '" + username.trim() + "' registered successfully!");
        } else {
            System.out.println("Failed to create/register user.");
        }
    }

    public void displayAllUsers() {
        printUserTable("\n----- Active Users -----", userService.getAllActiveUsers(), "No active users found.");
    }

    public void promptUpdateUser() {
        System.out.println("\n=== UPDATE User ===");
        displayAllUsers();
        int id = promptForInt("Enter user ID to update: ");
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        boolean updated = userService.updateUser(id, username, email);
        if (updated) {
            System.out.println("User ID " + id + " updated successfully!");
        } else {
            System.out.println("No user updated (check ID or input).");
        }
    }

    public void promptSearchUsers() {
        System.out.println("\n=== SEARCH Users ===");
        System.out.print("Enter username keyword: ");
        String keyword = scanner.nextLine();

        List<User> matches = userService.searchUsers(keyword);
        printUserTable("\n----- Search Users (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active users found.");
    }

    public void promptArchiveUser() {
        System.out.println("\n=== ARCHIVE User ===");
        displayAllUsers();
        int id = promptForInt("Enter user ID to archive: ");

        boolean archived = userService.archiveUser(id);
        if (archived) {
            System.out.println("User ID " + id + " archived successfully!");
        } else {
            System.out.println("No user archived (check ID or input).");
        }
    }

    public void promptRestoreUser() {
        System.out.println("\n=== RESTORE User ===");
        List<User> archivedUsers = userService.getAllArchivedUsers();
        printUserTable("\n----- Archived Users -----", archivedUsers, "No archived users found.");

        if (archivedUsers == null || archivedUsers.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter user ID to restore: ");
        boolean restored = userService.restoreUser(id);
        if (restored) {
            System.out.println("User ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived user restored (check ID or input).");
        }
    }

    public void promptDeleteUser() {
        System.out.println("\n=== DELETE User ===");
        displayAllUsers();
        int id = promptForInt("Enter user ID to permanently delete: ");

        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            System.out.println("User ID " + id + " deleted successfully!");
        } else {
            System.out.println("No user deleted (check ID or input).");
        }
    }

    private void printUserTable(String header, List<User> users, String emptyMessage) {
        System.out.println(header);
        System.out.printf("%-4s %-18s %-26s %-10s%n", "ID", "Username", "Email", "Role");

        if (users == null || users.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (User user : users) {
            System.out.printf("%-4d %-18s %-26s %-10s%n",
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole());
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