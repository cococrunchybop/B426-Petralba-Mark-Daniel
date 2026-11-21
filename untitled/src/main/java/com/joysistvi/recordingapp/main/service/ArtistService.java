package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllActiveArtists();

    boolean createArtist(String name);

    // TODO: getAllArchivedArtists, updateArtist, deleteArtist, archiveArtist, restoreArtist, searchArtists
}
