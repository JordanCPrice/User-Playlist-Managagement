package com.revature;

import com.revature.DAOs.SongDao;
import com.revature.DAOs.UserDAO;
import com.revature.models.Song;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("Conenction successful");
        }catch (SQLException e){
            e.printStackTrace();
        }

       UserDAO uDao = new UserDAO();
//        User u = uDao.updateUsername(uDao.findByUsername("JayXPee"), "JP");


        //System.out.println(uDao.getAllUsers());

        Song s = new Song("Bangarang", "Skrillex");
        SongDao sDao = new SongDao();
        sDao.addSong(s);
    }
}
