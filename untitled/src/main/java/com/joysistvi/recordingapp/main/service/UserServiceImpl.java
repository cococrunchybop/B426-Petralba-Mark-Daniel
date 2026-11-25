package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.User;
import com.joysistvi.recordingapp.main.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findAllActive();
    }

    @Override
    public List<User> getAllArchivedUsers() {
        return userRepository.findAllArchived();
    }

    @Override
    public boolean createUser(String username, String rawPassword, String email) {
        if (!isValidUserProfile(username, email)) {
            return false;
        }
        if (rawPassword == null || rawPassword.trim().length() < 6) {
            System.out.println("Validation Error: Password must be at least 6 characters.");
            return false;
        }
        if (userRepository.findByUsername(username.trim()) != null) {
            System.out.println("Validation Error: Username already exists.");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        User user = new User(username.trim(), hashedPassword, email.trim(), "USER");
        return userRepository.save(user);
    }

    @Override
    public boolean updateUser(int id, String username, String email) {
        if (id <= 0) {
            System.out.println("Validation Error: User ID must be greater than 0.");
            return false;
        }
        if (!isValidUserProfile(username, email)) {
            return false;
        }

        User user = new User(id, username.trim(), null, email.trim(), null);
        return userRepository.update(user);
    }

    @Override
    public boolean deleteUser(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: User ID must be greater than 0.");
            return false;
        }
        return userRepository.deleteById(id);
    }

    @Override
    public boolean archiveUser(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: User ID must be greater than 0.");
            return false;
        }
        return userRepository.archiveById(id);
    }

    @Override
    public boolean restoreUser(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: User ID must be greater than 0.");
            return false;
        }
        return userRepository.restoreById(id);
    }

    @Override
    public List<User> searchUsers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Search keyword cannot be blank.");
            return new ArrayList<>();
        }
        return userRepository.searchByUsername(keyword.trim());
    }

    @Override
    public User loginUser(String username, String rawPassword) {
        if (username == null || username.trim().isEmpty() || rawPassword == null || rawPassword.isEmpty()) {
            System.out.println("Validation Error: Username and password are required.");
            return null;
        }

        User existing = userRepository.findByUsername(username.trim());
        if (existing == null || existing.getPassword() == null) {
            return null;
        }

        try {
            if (BCrypt.checkpw(rawPassword, existing.getPassword())) {
                return existing;
            }
        } catch (IllegalArgumentException e) {
            // stored password is not a valid BCrypt hash (e.g. inserted manually as plain text)
            System.out.println("Account error: this user's password is not stored securely. Ask an admin to reset it.");
        }
        return null;
    }

    private boolean isValidUserProfile(String username, String email) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Validation Error: Username cannot be blank.");
            return false;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            System.out.println("Validation Error: Please enter a valid email address.");
            return false;
        }
        return true;
    }
}