package com.foodapp.food_ordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodapp.food_ordering.dto.AuthRequest;
import com.foodapp.food_ordering.dto.AuthResponse;
import com.foodapp.food_ordering.entity.User;
import com.foodapp.food_ordering.repository.UserRepository;
import com.foodapp.food_ordering.security.JwtUtil;

import java.util.Optional;

// This class handles all login and register logic
@Service
public class AuthService {

    // Connects to users table in database
    @Autowired
    private UserRepository userRepository;

    // Used to encrypt passwords before saving
    // and to check passwords during login
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Used to create JWT tokens after login
    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER - creates a new user account
    public AuthResponse register(AuthRequest request) {

        // Check if email already exists in database
        // We don't want two users with same email!
        if(userRepository.findByEmail(
                request.getEmail()).isPresent()) {
            throw new RuntimeException(
                "Email already registered!");
        }

        // Create new User object
        User user = new User();

        // Set name from request
        user.setName(request.getName());

        // Set email from request
        user.setEmail(request.getEmail());

        // ENCRYPT password before saving!
        // Never save plain text passwords!
        // passwordEncoder.encode turns
        // "password123" into "$2a$10$xyz..."
        user.setPassword(
            passwordEncoder.encode(request.getPassword()));

        // First user gets ADMIN role
        // All other users get USER role
        long totalUsers = userRepository.count();
        if(totalUsers == 0) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        // Save user to database
        userRepository.save(user);

        // Generate JWT token for this user
        String token = jwtUtil.generateToken(
                               user.getEmail());

        // Return token and user details to React
        return new AuthResponse(
            token,
            user.getEmail(),
            user.getRole(),
            "Registration successful!"
        );
    }

    // LOGIN - checks credentials and returns token
    public AuthResponse login(AuthRequest request) {

        // Find user by email in database
        Optional<User> userOptional =
            userRepository.findByEmail(request.getEmail());

        // If user not found - throw error
        if(userOptional.isEmpty()) {
            throw new RuntimeException(
                "User not found!");
        }

        // Get the actual user object
        User user = userOptional.get();

        // Check if password matches
        // passwordEncoder.matches compares:
        // "password123" with "$2a$10$xyz..."
        boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
        );

        // If password wrong - throw error
        if(!passwordMatches) {
            throw new RuntimeException(
                "Wrong password!");
        }

        // Generate JWT token for this user
        String token = jwtUtil.generateToken(
                               user.getEmail());

        // Return token and user details to React
        return new AuthResponse(
            token,
            user.getEmail(),
            user.getRole(),
            "Login successful!"
        );
    }
}
