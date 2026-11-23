package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.Song;
import java.util.List;

public interface SongRepository {
    List<Song> findAllActive();
    List<Song> findAllArchived();
    boolean save(Song song);
    boolean update(Song song);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Song> searchByTitle(String keyword);
}
