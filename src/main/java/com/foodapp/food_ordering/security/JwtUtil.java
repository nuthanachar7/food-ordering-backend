package com.foodapp.food_ordering.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

// @Component tells Spring Boot to manage this class
// So we can use @Autowired anywhere
@Component
public class JwtUtil {

    // Secret key used to sign our tokens
    // Must be at least 32 characters long!
    // In production - put this in environment variables!
    private static final String SECRET =
        "myFoodAppSecretKeyThatIsVeryLongAndSecure123456";

    // Token expires after 24 hours
    // 86400000 milliseconds = 24 hours
    private static final long EXPIRATION = 86400000;

    // Convert our secret string into a Key object
    // Keys.hmacShaKeyFor creates a secure key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // CREATE a new token for a user
    // Called after successful login or register
    // email = who this token belongs to
    public String generateToken(String email) {
        return Jwts.builder()
            // setSubject = who owns this token
            .setSubject(email)
            // setIssuedAt = when was token created
            .setIssuedAt(new Date())
            // setExpiration = when token expires
            .setExpiration(new Date(
                System.currentTimeMillis() + EXPIRATION))
            // signWith = sign with our secret key
            .signWith(getSigningKey(),
                      SignatureAlgorithm.HS256)
            // compact = convert to string
            .compact();
    }

    // GET the email from inside a token
    // Called when we want to know who sent request
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // CHECK if a token is valid
    // Returns true if valid, false if expired or wrong
    public boolean isTokenValid(String token) {
        try {
            // If getClaims works - token is valid!
            getClaims(token);
            return true;
        } catch (Exception e) {
            // If any error - token is invalid!
            return false;
        }
    }

    // GET all information stored inside token
    // Claims = all data inside the token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
            // Use our secret key to verify
            .setSigningKey(getSigningKey())
            .build()
            // Parse the token
            .parseClaimsJws(token)
            // Get the body (actual data)
            .getBody();
    }
}