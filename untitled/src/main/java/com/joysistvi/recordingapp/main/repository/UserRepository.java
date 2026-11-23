package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.User;
import java.util.List;

public interface UserRepository {
    List<User> findAllActive();
    List<User> findAllArchived();
    User findByUsername(String username);
    boolean save(User user);
    boolean update(User user);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<User> searchByUsername(String keyword);
}