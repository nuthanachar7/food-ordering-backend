package com.foodapp.food_ordering.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// This tells Spring Boot this class = users table in MySQL
@Entity
// The table will be named "users"
@Table(name = "users")
public class User {

    // Auto generated ID like 1, 2, 3...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User's full name like "Nuthan"
    private String name;

    // User's email - must be unique!
    // No two users can have same email
    @Column(unique = true)
    private String email;

    // Encrypted password - NEVER store plain text!
    private String password;

    // Role is either "ADMIN" or "USER"
    private String role;

    // Getters - used to READ values
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // Setters - used to SET values
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setRole(String role) { this.role = role; }
}