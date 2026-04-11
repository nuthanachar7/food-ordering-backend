package com.foodapp.food_ordering.dto;

// This holds the data we send BACK to React
// after successful login or register
public class AuthResponse {

    // JWT token - like a hotel key card!
    // React will store this and send it
    // with every future request
    private String token;

    // User's email
    private String email;

    // User's role - either ADMIN or USER
    private String role;

    // Success or error message
    private String message;

    // Constructor - creates object with all values
    public AuthResponse(String token, String email,
                        String role, String message) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.message = message;
    }

    // Getters - to READ values
    public String getToken() { return token; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMessage() { return message; }
}