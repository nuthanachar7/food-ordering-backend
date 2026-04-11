package com.foodapp.food_ordering.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.food_ordering.entity.User;

// This tells Spring Boot this talks to database
@Repository
// JpaRepository gives us free methods for User table
// User = which table, Long = type of ID
public interface UserRepository 
       extends JpaRepository<User, Long> {

    // Find user by their email address
    // Spring Data JPA creates this SQL automatically:
    // SELECT * FROM users WHERE email = ?
    // Optional means - user may or may not exist!
    Optional<User> findByEmail(String email);
}