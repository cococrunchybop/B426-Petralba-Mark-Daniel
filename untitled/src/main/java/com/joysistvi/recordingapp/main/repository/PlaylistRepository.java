package com.joysistvi.recordingapp.main.repository;

import com.joysistvi.recordingapp.main.model.Playlist;
import com.joysistvi.recordingapp.main.model.Song;
import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findAllActive();
    List<Playlist> findAllArchived();
    boolean save(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Playlist> searchByName(String keyword);

    // Additional Methods

    // Fetches all active playlists owned by a specific user
    // (used to view/manage their playlist and enforce the one-playlist-per-user rule).
    List<Playlist> findByUserId(int userId);

    // Fetches all active songs assigned to a given playlist via the playlist_songs junction table.
    List<Song> findSongsByPlaylistId(int playlistId);

    boolean addSongToPlaylist(int playlistId, int songId);

    boolean removeSongFromPlaylist(int playlistId, int songId);
}
