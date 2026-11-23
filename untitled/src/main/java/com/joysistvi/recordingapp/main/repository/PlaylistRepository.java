package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.Playlist;
import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findAllActive();
    List<Playlist> findAllArchived();
    boolean save(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Playlist> searchByName(String keyword);
}
