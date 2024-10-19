package com.revature.controllers;

import com.revature.DAOs.AuthDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

// this controller is for handling Authentication functionalities like login
public class AuthController {


    AuthDAO aDAO = new AuthDAO();

    // HttpSession object to store user info after successful login
    // This object will be instantiated upon successful login, letting a user access the app
    public static HttpSession ses;

    public Handler loginHandler = ctx -> {

        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

        User loggedInUser = aDAO.login(lDTO.getUser_id(), lDTO.getUsername());

        if (loggedInUser != null){

            // Create a session object
            ses = ctx.req().getSession();

            ses.setAttribute("username", loggedInUser.getUsername());
            ses.setAttribute("full_name", loggedInUser.getFull_name());

            System.out.println("Username: " + ses.getAttribute("username"));

            ctx.status(200);
            ctx.result("Login successful! Welcome, " + ses.getAttribute("username") + "!");

        }else{
            ctx.status(401);
            ctx.result("Login failed! Please double check ID/Username and try again.");
        }
    };
}
