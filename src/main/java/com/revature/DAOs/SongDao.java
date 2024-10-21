package com.revature.DAOs;

import com.revature.models.Song;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongDao implements SongDAOInterface{


    @Override
    public Song addSong(Song song) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT into songs (title, artist) VALUES (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,song.getTitle());
            ps.setString(2, song.getArtist());

            ps.executeUpdate();

            System.out.println(song.getTitle() + " was successfully added!");

            return song;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't add song");
        }

        return null;
    }

    @Override
    public ArrayList<Song> getAllSongs() {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM songs";

            // We can use a statement since there are no variables
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            ArrayList<Song> songs = new ArrayList<>();

            while (rs.next()){
                Song s = new Song(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getString("artist")
                );

                songs.add(s);
            }
            return songs;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error retrieving all users");
        }
        return null;

    }

    public List<Song> getSongsByPlaylist(int playlist_id) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.song_id, s.title, s.artist FROM songs s " +
                "JOIN playlist_songs ps ON s.song_id = ps.song_id " +
                "WHERE ps.playlist_id = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlist_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song song = new Song();
                song.setSong_id(rs.getInt("song_id"));
                song.setTitle(rs.getString("title"));
                song.setArtist(rs.getString("artist"));
                songs.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }
}
