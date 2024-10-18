package com.revature;

import com.revature.DAOs.PlaylistDAO;
import com.revature.DAOs.SongDao;
import com.revature.DAOs.UserDAO;
import com.revature.models.Playlist;
import com.revature.models.Song;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("Conenction successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        UserDAO uDao = new UserDAO();
//        User user = new User("choclolateecake", "George Washington");
        //uDao.addNewUser(user);
        //uDao.deleteUser(user);
        //uDao.updateUsername("JXP", "JP");


        //System.out.println(uDao.getAllUsers());

        Song s = new Song("Bangarang", "Skrillex");
        SongDao sDao = new SongDao();
        sDao.addSong(s);

        PlaylistDAO pDAO = new PlaylistDAO();
//        //System.out.println(pDAO.createNewPlaylist("NEW PLAYLIST", "JP"));
        //List<Song> songs = pDAO.getUsersPlaylist(1, 1);
        //for (Song song : songs) {
            //System.out.println(song.toString());
//        }


        //Playlist plist = new Playlist("awseomename", 1);
            //pDAO.createNewPlaylist("awesomename", "JP");

        //pDAO.deletePlaylist("awesomename");

        }
    }

