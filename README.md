
# User and Playlist Management Application


## Introduction

This application is designed for managing users and their playlists. It allows users to create, update, and delete their profiles and playlists. The application is built using Java with Javalin for the web framework and PostgreSQL for the database.

## Technologies Used

- **Java**: Programming language used for building the application.
- **Javalin**: Lightweight web framework for creating RESTful APIs.
- **PostgreSQL**: Relational database for storing user and playlist data.
- **JDBC**: Java Database Connectivity for connecting and executing queries with the database.
- **Maven**: Build automation tool used for dependency management.


## API Endpoints

### Users

- **GET /users**: Retrieve all users.
- **POST /users**: Create a new user.
- **PATCH /users/{id}**: Update the username of a user.
- **DELETE /users/{id}**: Delete a user by ID.
- **GET /users/{id}**: Retrieve a user by ID.

### Playlists

- **GET /users/{id}**: Retrieve all playlists for a specific user.
- **POST /playlist**: Create a new playlist for a user.
- **DELETE /playlists/{id}**: Delete a specific playlist by ID.

## Features

- Basic user registration and authentication.
- Create, update, and delete playlists.
- Fetch playlists based on user ID.
- Simple error handling for various scenarios (e.g., user not found, username already taken).
