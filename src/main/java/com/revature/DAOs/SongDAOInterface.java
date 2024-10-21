package com.revature.DAOs;

import com.revature.models.Song;

import java.util.ArrayList;
import java.util.List;

public interface SongDAOInterface {

    Song addSong(Song song);

    ArrayList<Song> getAllSongs();

    List<Song> getSongsByPlaylist(int playlist_id);
}

