package com.revature.DAOs;

import com.revature.models.Song;

import java.util.ArrayList;

public interface SongDAOInterface {

    Song addSong(Song song);

    ArrayList<Song> getAllSongs();
}
