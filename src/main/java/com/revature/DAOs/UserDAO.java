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
            System.out.println("Error, ID not found");
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
    public User updateUsername(String username, String newUserName) {
        UserDAO uDAO = new UserDAO();

        User user = uDAO.findByUsername(username);

        try(Connection conn = ConnectionUtil.getConnection()){

            //Represents the SQL query
            String sql = "UPDATE users SET username = ? WHERE username = ?";

            // PreparedStatement helps sanitize data to prevent SQL injection
            PreparedStatement ps = conn.prepareStatement(sql);

            // Populates the first ? with the parameter id
            ps.setString(1,newUserName);
            ps.setString(2, user.getUsername());

            // Executes the query and saves the result in the ResultSet
            ps.executeUpdate();

            System.out.println("Congratulations " + username + " you have successfully updated your username to " + newUserName + "!");
            return user;

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
    public User addNewUser(User user){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO users(username, full_name) VALUES(?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFull_name());

            ps.executeUpdate();

            System.out.println("Welcome to our newest user " + user.getUsername() + "!");

            return user;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error when adding new user.");
        }
        return null;
    }

    public void deleteUser(User user){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM users WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,user.getUsername());

            ps.executeUpdate();
            System.out.println(user.getUsername() + " was deleted successfully");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error when deleting user");
        }
    }
}
