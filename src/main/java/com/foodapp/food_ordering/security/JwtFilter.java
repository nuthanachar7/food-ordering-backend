package com.foodapp.food_ordering.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.annotation.Lazy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// Runs on EVERY request to check JWT token
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Lazy

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // Get Authorization header
        // Looks like: "Bearer eyJhbGci..."
        String authHeader = 
            request.getHeader("Authorization");

        String token = null;
        String email = null;

        // Check header exists and starts with Bearer
        if(authHeader != null &&
           authHeader.startsWith("Bearer ")) {
            // Remove "Bearer " to get just token
            token = authHeader.substring(7);
            // Get email from token
            email = jwtUtil.extractEmail(token);
        }

        // If email found and not authenticated yet
        if(email != null &&
           SecurityContextHolder.getContext()
           .getAuthentication() == null) {

            // Load user from database
            UserDetails userDetails =
                userDetailsService
                .loadUserByUsername(email);

            // If token is valid
            if(jwtUtil.isTokenValid(token)) {

                // Create authentication object
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities());

                auth.setDetails(
                    new WebAuthenticationDetailsSource()
                    .buildDetails(request));

                // Set authentication in context
                SecurityContextHolder.getContext()
                    .setAuthentication(auth);
            }
        }
        // Continue to next filter
        filterChain.doFilter(request, response);
    }
}