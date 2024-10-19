package com.revature.controllers;

import com.revature.DAOs.PlaylistDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.Playlist;
import com.revature.models.User;
import io.javalin.http.Handler;

import java.util.List;

public class PlaylistController {


    PlaylistDAO pDAO = new PlaylistDAO();
    UserDAO uDao = new UserDAO();

    public Handler getPlayListByIDHandler = ctx -> {

        int user_id_fk = Integer.parseInt(ctx.pathParam("id"));

        // Use the DAO to get the playlist based on user_id and playlist_id
        List<String> playlistName = pDAO.getPlaylistByUser(user_id_fk);

        if (playlistName != null) {
            // If playlist is found, return it as JSON
            ctx.status(200);
            ctx.json(playlistName);
        } else {
            // If no playlist is found, return a 404 status with a message
            ctx.status(404);
            ctx.result("Playlist not found");
        }
    };

    public Handler addNewPlaylistHandler = ctx -> {

        Playlist plist = ctx.bodyAsClass(Playlist.class);

        if ((plist.getPlaylist_name() == null) || plist.getPlaylist_name().isBlank()){
            ctx.result("Must enter Playlist name");
            ctx.status(400);
            return;
        }
        int userID = plist.getUser_id_fk();

        User user = uDao.getUserById(userID);

        if (user == null){
            ctx.result("Invalid username");
            ctx.status(400);
            return;
        }

        Playlist createdPlaylist = pDAO.createNewPlaylist(plist.getPlaylist_name(), user.getUsername());

        if (createdPlaylist != null){
            ctx.status(201); //Created
            ctx.json(createdPlaylist);
        }else{
            ctx.result("Couldn't create playlist");
        }
    };
}
