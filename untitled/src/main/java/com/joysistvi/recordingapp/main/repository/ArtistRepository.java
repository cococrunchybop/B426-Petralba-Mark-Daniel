package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

public interface ArtistRepository {

    List<Artist> findAllActive();

    boolean save(Artist artist);

    // TODO: findAllArchived, update, deleteById, archiveById, restoreById, searchByName
}
