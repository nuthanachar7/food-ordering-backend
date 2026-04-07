package com.foodapp.food_ordering;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// This tells Spring Boot this is a configuration class
@Configuration
public class Corsconfig {

    // This method allows React to talk to Spring Boot!
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Allow requests from React!
                        .allowedOrigins("http://localhost:3000",
                        				"https://food-ordering-frontend-flame.vercel.app")
                        // Allow these HTTP methods
                        .allowedMethods("GET", "POST", 
                                        "PUT", "DELETE");
            }
        };
    }
}
