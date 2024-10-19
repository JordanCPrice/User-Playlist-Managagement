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
    public boolean updateUsername(int userId, String newUsername) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET username = ? WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newUsername);  // Set the new username
            ps.setInt(2, userId);          // Use the userId directly

            int rowsUpdated = ps.executeUpdate();

            // If at least one row was updated, return true
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
            return false;         // Handle as needed
        }
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

        UserDAO uDAO = new UserDAO();

        if (uDAO.checkIfUsernameExist(user.getUsername())){
            System.out.println("Username is already taken");
            return null;
        }

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

    public void deleteUser(int user_id){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "DELETE FROM users WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,user_id);

            ps.executeUpdate();
            System.out.println("User with ID " + user_id + " was deleted successfully");

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error when deleting user");
        }
    }

    public boolean checkIfUsernameExist(String username){

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM users where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt(1) > 0; // Return true if count > 0 (username exists)
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
