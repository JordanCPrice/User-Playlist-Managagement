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

    public Handler deletePlaylistHandler = ctx -> {

        int playlist_id = Integer.parseInt(ctx.pathParam("id"));

        if (playlist_id <= 0) {
            ctx.result("ID must be greater than 0!");
            ctx.status(400);
            return;
        }

        Playlist pList = pDAO.getPlaylistById(playlist_id);
        if (pList == null) {
            ctx.result("Playlist with ID " + playlist_id + " does not exist.");
            ctx.status(404); // Not found
            return;
        }

        // Proceed with deleting the user
        pDAO.deletePlaylist(playlist_id);
        ctx.result("Playlist " + pList.getPlaylist_name() + " was deleted successfully.");
        ctx.status(200); // OK
    };
}
