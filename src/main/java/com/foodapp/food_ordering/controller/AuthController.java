package com.foodapp.food_ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.food_ordering.dto.AuthRequest;
import com.foodapp.food_ordering.dto.AuthResponse;
import com.foodapp.food_ordering.service.AuthService;

// This class handles all auth API requests
@RestController
// All APIs in this class start with /api/auth
@RequestMapping("/api/auth")
public class AuthController {

    // Connects to AuthService
    @Autowired
    private AuthService authService;

    // POST /api/auth/register
    // Called when user creates new account
    // @RequestBody reads JSON from React
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
           @RequestBody AuthRequest request) {

        // Call service to register user
        AuthResponse response = 
            authService.register(request);

        // Return response with 200 OK status
        return ResponseEntity.ok(response);
    }

    // POST /api/auth/login
    // Called when user logs in
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RequestBody AuthRequest request) {

        // Call service to login user
        AuthResponse response = 
            authService.login(request);

        // Return response with 200 OK status
        return ResponseEntity.ok(response);
    }
}
