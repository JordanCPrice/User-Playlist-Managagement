package com.revature.DAOs;

import com.revature.models.Song;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;


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
}
