package com.example.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // Izinkan semua endpoint di "/api/"
                        .allowedOrigins("http://localhost:8080") // Izinkan frontend di port 8080
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode yang diizinkan
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
