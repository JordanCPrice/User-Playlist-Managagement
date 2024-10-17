package com.revature.models;

public class Playlist {


    private int playlist_id;
    private String playlist_name;
    private int user_id_fk;

    public Playlist() {
    }

    public Playlist(int playlist_id, String playlist_name, int user_id_fk) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
        this.user_id_fk = user_id_fk;
    }
}
