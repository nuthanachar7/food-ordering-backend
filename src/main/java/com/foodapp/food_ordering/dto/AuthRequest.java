package com.foodapp.food_ordering.dto;

// DTO = Data Transfer Object
// This holds the data sent from React when
// user registers or logs in
public class AuthRequest {

    // User's full name - only needed for register
    private String name;

    // User's email address
    private String email;

    // User's password
    private String password;

    // Getters - to READ values
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters - to SET values
    public void setName(String name) { 
        this.name = name; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
}