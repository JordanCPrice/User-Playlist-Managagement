package com.revature.DAOs;

import com.revature.models.Song;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SongDao implements SongDAOInterface{


    @Override
    public Song addSong(Song song) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT into songs (title, artist) VALUES (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,song.getTitle());
            ps.setString(2, song.getArtist());

            ps.executeUpdate();

            return song;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't add song");
        }

        return null;
    }
}
