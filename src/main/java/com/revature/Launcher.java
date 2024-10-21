package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.PlaylistController;
import com.revature.controllers.SongController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class Launcher {

    public static void main(String[] args) {


        // Javalin Set-up, instantiates Javalin object on port 7000
        var app = Javalin.create().start(7000);

        // BEFORE HANDLER! Check for a not-null session for all paths except the login path
        app.before("/*", ctx -> {
            // Allow access to the login endpoint without a session
            if (!ctx.path().equals("/auth")) {
                checkSession(ctx);
            }
        });


        // Exception handler for unauthorized access
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });


        UserController uc = new UserController();
        PlaylistController pc = new PlaylistController();
        SongController sc = new SongController();
        AuthController ac = new AuthController();

        //app.get is the Javalin handler which takes in GET requests
        // It is calling the getUserHandler method from the UserController class
        // When we send a get request to localhost:7000/users it gets routed here.

        app.get("/users", uc.getUsersHandler);

        //app.post is the Javalin handler for post requests
        app.post("/users", uc.insertNewUserHandler);

        // .patch for patch requests
        app.patch("/users/{id}", uc.updateUsernameHandler);

        // .delete for delete requests
        app.delete("/users/{id}", uc.deleteUserHandler);

        app.get("/songs", sc.getAllSongsHandler);

        app.get("/users/{id}", pc.getPlayListByIDHandler);

        app.post("/playlist", pc.addNewPlaylistHandler);

        app.delete("/playlist/{id}", pc.deletePlaylistHandler);

        app.post("/auth", ac.loginHandler);

        app.get("/playlist/{playlist_id}/songs", sc.getSongsFromPlaylistHandler);
        }


    // Method to check session and handle unauthorized access
    private static void checkSession(Context ctx) {
        if (AuthController.ses == null) {
            throw new IllegalArgumentException("You must log in before doing this!");
        }
    }

    }
