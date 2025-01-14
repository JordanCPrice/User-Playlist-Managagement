package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import io.javalin.http.Handler;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    UserDAO uDAO = new UserDAO();

    public Handler getUsersHandler = ctx -> {

        //Populates ArrayList w/ all users from DB.
        ArrayList<User> users = uDAO.getAllUsers();

        //Need to convert each user to JSON.
        ctx.json(users);

        //Set status code
        ctx.status(200);
    };

    public Handler insertNewUserHandler = ctx -> {

        //We have JSON coming in (we're sending an User object through Postman)
        // We need to convert that JSON to a Java object before we can send it to the DAO
        // We use bodyAsClass() method to do this (HTTP request body --> Java object)
        User newUser = ctx.bodyAsClass(User.class);

        // Validate the input
        if ((newUser.getUsername() == null) || newUser.getUsername().isBlank()) {
            ctx.result("Username is required!");
            ctx.status(400); // Bad request
            return;
        }

        if (newUser.getFull_name() == null || newUser.getFull_name().isBlank()) {
            ctx.result("Full name is required!");
            ctx.status(400); // Bad request
            return;
        }

        // Check if the username already exists
        if (uDAO.checkIfUsernameExist(newUser.getUsername())) {
            ctx.result("Username is already taken!");
            ctx.status(400); // Bad request
            return;
        }

        // Insert new user if username is not taken
        User insertedUser = uDAO.addNewUser(newUser);

        // Return success message and status
        ctx.status(201); // Created
        ctx.result("User " + insertedUser.getUsername() + " was successfully created!");
        ctx.json(insertedUser);
    };

    public Handler updateUsernameHandler = ctx -> {
        int user_id = Integer.parseInt(ctx.pathParam("id"));  // Get user ID from path
        String requestedUsername = ctx.body();  // New username from request body


        // Check if the requested username is blank
        if (requestedUsername.isBlank()) {
            ctx.result("Requested username cannot be blank.");
            ctx.status(400);  // Bad request
            return;  // Exit if the username is blank
        }


        // Check if the user exists
        User user = uDAO.getUserById(user_id);
        if (user == null) {
            ctx.result("User with ID " + user_id + " does not exist.");
            ctx.status(404);  // Not Found
            return;
        }
        String oldUsername = uDAO.getUserById(user_id).getUsername();

        // Check if the requested username already exists
        if (uDAO.checkIfUsernameExist(requestedUsername)) {
            ctx.result(requestedUsername + " is already taken, please choose a different username!");
            ctx.status(400);  // Bad request
            return;
        }

        boolean isUpdated = uDAO.updateUsername(user_id, requestedUsername);

        if (isUpdated) {
            ctx.result(oldUsername + " is now known as " + requestedUsername + "!");
            ctx.status(200);  // OK
        } else {
            ctx.result("Failed to update username. " + oldUsername + " not found.");
            ctx.status(404);  // Not Found
        }
    };

    public Handler deleteUserHandler = ctx -> {

        int user_id = Integer.parseInt(ctx.pathParam("id"));

        if (user_id <= 0) {
            ctx.result("ID must be greater than 0!");
            ctx.status(400);
            return;
        }

        User user = uDAO.getUserById(user_id);
        if (user == null) {
            ctx.result("User with ID " + user_id + " does not exist.");
            ctx.status(404); // Not found
            return;
        }

        // Proceed with deleting the user
        uDAO.deleteUser(user_id);
        ctx.result("User " + user.getUsername() + " was deleted successfully.");
        ctx.status(200); // OK
    };
}
