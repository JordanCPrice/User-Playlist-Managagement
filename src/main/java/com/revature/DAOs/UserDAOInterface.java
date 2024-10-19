package com.revature.DAOs;

/* Using an Interface to lay out all the methods that must be implemented.
   Useful for documentation (imagine many methods)  */

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    // Return user based on ID
    User getUserById(int id);

    // Allows user to update Username
    public boolean updateUsername(int userId, String newUsername);

    ArrayList<User> getAllUsers();

    User addNewUser(User user);

    void deleteUser(int user_id);

    boolean checkIfUsernameExist(String username);

}
