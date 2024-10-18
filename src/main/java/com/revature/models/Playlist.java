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
    public Playlist(String playlist_name, int user_id_fk){
        this.playlist_name = playlist_name;
        this.user_id_fk = user_id_fk;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public int getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(int user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlist_id=" + playlist_id +
                ", playlist_name='" + playlist_name + '\'' +
                ", user_id_fk=" + user_id_fk +
                '}';
    }
}
