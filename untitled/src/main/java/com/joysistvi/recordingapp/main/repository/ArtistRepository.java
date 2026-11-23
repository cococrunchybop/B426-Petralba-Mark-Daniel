package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.Artist;

import java.util.List;

// Repository(Data Tier) -> Data Access Logic and Data Persistence
public interface ArtistRepository {

    List<Artist> findAllActive();

    List<Artist> findAllArchived();

    boolean save(Artist artist);

    boolean update(Artist artist);

    boolean deleteById(int id);

    boolean archiveById(int id);

    boolean restoreById(int id);

    List<Artist> searchByName(String keyword);

}
