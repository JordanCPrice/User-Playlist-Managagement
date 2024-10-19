package com.revature.models;

public class Song {

    private int song_id;
    private String title;
    private String artist;

    public Song() {
    }

    public Song(int song_id, String title, String artist) {
        this.song_id = song_id;
        this.title = title;
        this.artist = artist;
    }
    public Song (String title, String artist){
        this.title = title;
        this.artist = artist;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song_id=" + song_id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
