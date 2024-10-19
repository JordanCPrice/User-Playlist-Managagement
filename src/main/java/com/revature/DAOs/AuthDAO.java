package com.revature.DAOs;


import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This DAO will handle Login
public class AuthDAO {


public User login(int user_id, String username){

    try(Connection conn = ConnectionUtil.getConnection()){

        String sql = "SELECT * FROM users WHERE user_id = ? and username = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1,user_id);
        ps.setString(2, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("full_name")
            );
            return user;
        }
    }catch(SQLException e){
        e.printStackTrace();
        System.out.println("Couldn't login user");
    }

    return null;
}
}
