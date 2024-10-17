package com.revature.DAOs;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {




    @Override
    public User getUserById(int id) {

        try(Connection conn = ConnectionUtil.getConnection()){

            //Represents the SQL query
            String sql = "SELECT * FROM users WHERE user_id = ?";

            // PreparedStatement helps sanitize data to prevent SQL injection
            PreparedStatement ps = conn.prepareStatement(sql);

            // Populates the first ? with the parameter id
            ps.setInt(1, id);

            // Executes the query and saves the result in the ResultSet
            ResultSet rs = ps.executeQuery();

           if(rs.next()){
               User user = new User(
                       rs.getInt("user_id"),
                       rs.getString("username"),
                       rs.getString("full_name")
               );
               return user;
           }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error, Id not found");
        }

        return null;
    }

    // Have to be able to find users by username for updateUsername method.

    public User findByUsername(String username){

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("full_name")
                );
                return user;
            }

        }catch (SQLException e){
         e.printStackTrace();
            System.out.println("Error, Username not found");
        }
        return null;

    }

    @Override
    public User updateUsername(User username, String newUserName) {

        try(Connection conn = ConnectionUtil.getConnection()){

            //Represents the SQL query
            String sql = "UPDATE users SET username = ? WHERE username = ?";

            // PreparedStatement helps sanitize data to prevent SQL injection
            PreparedStatement ps = conn.prepareStatement(sql);

            // Populates the first ? with the parameter id
            ps.setString(1,newUserName);
            ps.setString(2, username.getUsername());

            // Executes the query and saves the result in the ResultSet
            ps.executeUpdate();


                return username;

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error updating username");
        }

        return null;
    }
    public ArrayList<User> getAllUsers(){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM users";

            // We can use a statement since there are no variables
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<User> users = new ArrayList<>();

            while (rs.next()){
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("full_name")
                );

                users.add(u);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error retrieving all users");
        }
        return null;
    }
}
