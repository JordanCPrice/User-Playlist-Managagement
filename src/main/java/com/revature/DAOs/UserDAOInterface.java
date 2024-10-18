package com.revature.DAOs;

/* Using an Interface to lay out all the methods that must be implemented.
   Useful for documentation (imagine many methods)  */

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    // Return user based on ID
    User getUserById(int id);

    // Allows user to update Username
    User updateUsername(String username, String newUserName);

    ArrayList<User> getAllUsers();

    User addNewUser(User user);

    void deleteUser(User user);

}
