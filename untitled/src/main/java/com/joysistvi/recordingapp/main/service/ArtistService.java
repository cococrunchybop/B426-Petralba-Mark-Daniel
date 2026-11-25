package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllActiveArtists();

    List<Artist> getAllArchivedArtists();

    boolean createArtist(String name);

    boolean updateArtist(int id, String name);

    boolean deleteArtist(int id);

    boolean archiveArtist(int id);

    boolean restoreArtist(int id);

    List<Artist> searchArtists(String keyword);

    Artist getArtistById(int id);

}
