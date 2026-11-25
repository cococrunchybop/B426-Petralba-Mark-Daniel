package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Song;
import com.joysistvi.recordingapp.main.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;

public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllActiveSongs() {
        return songRepository.findAllActive();
    }

    @Override
    public List<Song> getAllArchivedSongs() {
        return songRepository.findAllArchived();
    }

    @Override
    public boolean createSong(String title, String genre, String length, int albumId) {
        if (!isValidSongInput(title, genre, length, albumId)) {
            return false;
        }
        return songRepository.save(new Song(title.trim(), genre.trim(), length.trim(), albumId));
    }

    @Override
    public boolean updateSong(int id, String title, String genre, String length, int albumId) {
        if (id <= 0) {
            System.out.println("Validation Error: Song ID must be greater than 0.");
            return false;
        }
        if (!isValidSongInput(title, genre, length, albumId)) {
            return false;
        }
        Song song = new Song(id, title.trim(), genre.trim(), length.trim(), albumId, null);
        return songRepository.update(song);
    }

    @Override
    public boolean deleteSong(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Song ID must be greater than 0.");
            return false;
        }
        return songRepository.deleteById(id);
    }

    @Override
    public boolean archiveSong(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Song ID must be greater than 0.");
            return false;
        }
        return songRepository.archiveById(id);
    }

    @Override
    public boolean restoreSong(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Song ID must be greater than 0.");
            return false;
        }
        return songRepository.restoreById(id);
    }

    @Override
    public List<Song> searchSongs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Search keyword cannot be blank.");
            return new ArrayList<>();
        }
        List<Song> results = songRepository.searchByTitle(keyword.trim());
        return results != null ? results : new ArrayList<>();
    }

    private boolean isValidSongInput(String title, String genre, String length, int albumId) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Validation Error: Song title cannot be blank.");
            return false;
        }
        if (genre == null || genre.trim().isEmpty()) {
            System.out.println("Validation Error: Genre cannot be blank.");
            return false;
        }
        if (length == null || !length.trim().matches("\\d{1,2}:[0-5]\\d")) {
            System.out.println("Validation Error: Length must be in m:ss format (e.g. 3:45).");
            return false;
        }
        if (albumId <= 0) {
            System.out.println("Validation Error: Album ID must be greater than 0.");
            return false;
        }
        return true;
    }
}
