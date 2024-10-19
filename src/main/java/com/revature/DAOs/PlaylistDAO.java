package com.revature.DAOs;

import com.revature.models.Playlist;
import com.revature.models.Song;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO implements PlaylistDAOInterface{


    @Override
    public Playlist createNewPlaylist(String name, String username) {

        //Create new user to use find by username method to get and set fk
        UserDAO uDAO = new UserDAO();
        User user = uDAO.findByUsername(username);

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT into playlist (playlist_name, user_id_fk) VALUES (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,name);
            ps.setInt(2, user.getUser_id());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0){
                Playlist playlists = new Playlist();
                        playlists.setPlaylist_name(name);
                        playlists.setUser_id_fk(user.getUser_id());

                System.out.println("Yay " + user.getUsername() + " you successfully created the " + name + " playlist!");

                return playlists;
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't create new playlist");
        }
        return null;
    }

    @Override
    public List<Song> getUsersPlaylist(int user_id, int playlist_id) {
        //Create a list object to hold songs
        List<Song> songs = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT p.playlist_name, s.song_id, s.title, s.artist " +
                          "FROM songs s " +
                          "JOIN playlist_songs ps ON s.song_id = ps.song_id " +
                          "JOIN playlist p ON ps.playlist_id = p.playlist_id " +
                          "JOIN users u on p.user_id_fk = u.user_id " +
                          "WHERE u.user_id = ? AND p.playlist_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,user_id);
            ps.setInt(2,playlist_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Song song = new Song(
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getString("artist")
                );
                songs.add(song);
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't get Users playlist.");
        }
        return songs;
    }

    public void deletePlaylist(String name){
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM playlist WHERE playlist_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,name);

            ps.executeUpdate();
            System.out.println(name + " was deleted successfully");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error when deleting playlist");
        }
    }

    public List<String> getPlaylistByUser(int user_id) {
        List<String> playlistNames = new ArrayList<>();

        String sql = "SELECT playlist_name FROM playlist WHERE user_id_fk = ?";

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                playlistNames.add(rs.getString("playlist_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistNames;
    }
}
