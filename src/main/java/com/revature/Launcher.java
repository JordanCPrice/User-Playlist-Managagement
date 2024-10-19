package com.revature;

import com.revature.controllers.PlaylistController;
import com.revature.controllers.SongController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
public class Launcher {

    public static void main(String[] args) {


        // Javalin Set-up, instantiates Javalin object on port 7000
        var app = Javalin.create().start(7000);

        app.get("/", ctx -> ctx.result("Hello Javlin"));

        UserController uc = new UserController();
        PlaylistController pc = new PlaylistController();
        SongController sc = new SongController();

        //app.get is the Javalin handler which takes in GET requests
        // It is calling the getUserHandler method from the UserController class
        // When we send a get request to localhost:7000/users it gets routed here.

        app.get("/users", uc.getUsersHandler);

        //app.post is the Javalin handler for post requests
        app.post("/users", uc.insertNewUserHandler);

        app.patch("/users/{id}", uc.updateUsernameHandler);

        app.delete("/users/{id}", uc.deleteUserHandler);

        app.get("/songs", sc.getAllSongsHandler);

        app.get("/users/{id}", pc.getPlayListByIDHandler);

        app.post("/playlist", pc.addNewPlaylistHandler);

        }
    }
