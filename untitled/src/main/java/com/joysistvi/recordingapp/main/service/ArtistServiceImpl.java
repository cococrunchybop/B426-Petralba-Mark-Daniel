package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Artist;
import com.joysistvi.recordingapp.main.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllActiveArtists() {
        return artistRepository.findAllActive();
    }

    @Override
    public List<Artist> getAllArchivedArtists() {
        return artistRepository.findAllArchived();
    }

    @Override
    public boolean createArtist(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Artist name cannot be blank.");
            return false;
        }

        return artistRepository.save(new Artist(name.trim()));
    }

    @Override
    public boolean updateArtist(int id, String name) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Artist name cannot be blank.");
            return false;
        }
        Artist updated = new Artist(id, name.trim());
        return artistRepository.update(updated);
    }

    @Override
    public boolean deleteArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return false;
        }
        return artistRepository.deleteById(id);
    }

    @Override
    public boolean archiveArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return false;
        }
        return artistRepository.archiveById(id);
    }

    @Override
    public boolean restoreArtist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return false;
        }
        return artistRepository.restoreById(id);
    }

    @Override
    public List<Artist> searchArtists(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Search keyword cannot be blank.");
            return new ArrayList<>();
        }
        return artistRepository.searchByName(keyword.trim());
    }

    @Override
    public Artist getArtistById(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return null;
        }
        return artistRepository.findById(id);
    }
}
