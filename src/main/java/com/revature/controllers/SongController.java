package com.revature.controllers;

import com.revature.DAOs.SongDao;
import com.revature.models.Song;
import io.javalin.http.Handler;

import java.util.ArrayList;

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
}
