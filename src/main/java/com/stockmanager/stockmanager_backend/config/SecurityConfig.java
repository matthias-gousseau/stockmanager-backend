package com.stockmanager.stockmanager_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // désactive CSRF
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // autorise toutes les routes
        )
        .formLogin(login -> login.disable()) // désactive le formulaire de login
        .httpBasic(httpBasic -> httpBasic.disable()); // désactive l’auth basique

    return http.build();
  }
}