package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.Album;
import java.util.List;

public interface AlbumRepository {
    List<Album> findAllActive();
    List<Album> findAllArchived();
    boolean save(Album album);
    boolean update(Album album);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Album> searchByName(String keyword);
}