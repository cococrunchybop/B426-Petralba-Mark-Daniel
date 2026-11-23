package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllActiveUsers();
    List<User> getAllArchivedUsers();
    boolean createUser(String username, String rawPassword, String email);
    boolean updateUser(int id, String username, String email);
    boolean deleteUser(int id);
    boolean archiveUser(int id);
    boolean restoreUser(int id);
    List<User> searchUsers(String keyword);
    User loginUser(String username, String rawPassword);
}