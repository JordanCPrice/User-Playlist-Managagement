package com.revature.controllers;

import com.revature.DAOs.PlaylistDAO;
import com.revature.DAOs.SongDao;
import com.revature.models.Song;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class SongController {


    SongDao sDAO = new SongDao();

    public Handler getAllSongsHandler = ctx -> {

        //Populates ArrayList w/ all songs from DB.
        ArrayList<Song> songs = sDAO.getAllSongs();

        //Need to convert each user to JSON.
        ctx.json(songs);

        //Set status code
        ctx.status(200);
    };

    public Handler getSongsFromPlaylistHandler = ctx -> {
        int playlist_id = Integer.parseInt(ctx.pathParam("playlist_id"));  // Get playlist ID from path

        // Use DAO to fetch all songs for this playlist
        List<Song> songs = sDAO.getSongsByPlaylist(playlist_id);

        if (songs.isEmpty()) {
            ctx.status(404);
            ctx.result("No songs found for this playlist");
        } else {
            ctx.status(200);
            ctx.json(songs);
        }
    };
}
