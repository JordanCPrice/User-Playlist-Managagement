package com.revature.DAOs;

import com.revature.models.Playlist;
import com.revature.models.Song;

import java.util.List;

public interface PlaylistDAOInterface {

    Playlist createNewPlaylist(String name, String username);

    List<Song> getUsersPlaylist(int user_id, int playlist_id);

    void deletePlaylist(int playlist_id);

    List<String>getPlaylistByUser(int user_id);

    Playlist getPlaylistById(int id);
}


