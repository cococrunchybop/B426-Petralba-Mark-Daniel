package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Album;
import com.joysistvi.recordingapp.main.repository.AlbumRepository;
import java.util.ArrayList;
import java.util.List;

public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllActiveAlbums() {
        return albumRepository.findAllActive();
    }

    @Override
    public List<Album> getAllArchivedAlbums() {
        return albumRepository.findAllArchived();
    }

    @Override
    public boolean createAlbum(String name, int year, int artistId) {
        if (!isValidAlbumInput(name, year, artistId)) {
            return false;
        }
        return albumRepository.save(new Album(name.trim(), year, artistId));
    }

    @Override
    public boolean updateAlbum(int id, String name, int year, int artistId) {
        if (id <= 0) {
            System.out.println("Validation Error: Album ID must be greater than 0.");
            return false;
        }
        if (!isValidAlbumInput(name, year, artistId)) {
            return false;
        }
        Album album = new Album(id, name.trim(), year, artistId, null);
        return albumRepository.update(album);
    }

    @Override
    public boolean deleteAlbum(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Album ID must be greater than 0.");
            return false;
        }
        return albumRepository.deleteById(id);
    }

    @Override
    public boolean archiveAlbum(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Album ID must be greater than 0.");
            return false;
        }
        return albumRepository.archiveById(id);
    }

    @Override
    public boolean restoreAlbum(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Album ID must be greater than 0.");
            return false;
        }
        return albumRepository.restoreById(id);
    }

    @Override
    public List<Album> searchAlbums(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Search keyword cannot be blank.");
            return new ArrayList<>();
        }
        return albumRepository.searchByName(keyword.trim());
    }

    private boolean isValidAlbumInput(String name, int year, int artistId) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Album name cannot be blank.");
            return false;
        }
        if (year < 1900 || year > 2100) {
            System.out.println("Validation Error: Release year must be between 1900 and 2100.");
            return false;
        }
        if (artistId <= 0) {
            System.out.println("Validation Error: Artist ID must be greater than 0.");
            return false;
        }
        return true;
    }
}