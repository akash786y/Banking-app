package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // Allow all requests without authentication
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .httpBasic(httpBasic -> httpBasic.disable());  // Disable HTTP Basic Authentication

        return http.build();
    }
}