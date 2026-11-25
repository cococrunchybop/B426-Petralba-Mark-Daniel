package com.joysistvi.recordingapp.main.service;

import com.joysistvi.recordingapp.main.model.Playlist;
import com.joysistvi.recordingapp.main.model.Song;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllActivePlaylists();
    List<Playlist> getAllArchivedPlaylists();
    boolean createPlaylist(String name, int userId);
    boolean updatePlaylist(int id, String name, int userId);
    boolean deletePlaylist(int id);
    boolean archivePlaylist(int id);
    boolean restorePlaylist(int id);
    List<Playlist> searchPlaylists(String keyword);

    // Additional Methods
    List<Playlist> getPlaylistsByUser(int userId);

    List<Song> getSongsInPlaylist(int playlistId);

    boolean addSongToPlaylist(int playlistId, int songId);

    boolean removeSongFromPlaylist(int playlistId, int songId);
}
