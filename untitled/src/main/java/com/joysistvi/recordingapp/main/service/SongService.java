package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Song;
import java.util.List;

public interface SongService {
    List<Song> getAllActiveSongs();
    List<Song> getAllArchivedSongs();
    boolean createSong(String title, String genre, String length, int albumId);
    boolean updateSong(int id, String title, String genre, String length, int albumId);
    boolean deleteSong(int id);
    boolean archiveSong(int id);
    boolean restoreSong(int id);
    List<Song> searchSongs(String keyword);
}
