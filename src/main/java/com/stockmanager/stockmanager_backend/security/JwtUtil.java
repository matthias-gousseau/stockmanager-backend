package com.stockmanager.stockmanager_backend.security;

import com.stockmanager.stockmanager_backend.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

  private final SecretKey key;

  public JwtUtil(@Value("${jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .claim("id", user.getId())
        .claim("email", user.getEmail())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plus(Duration.ofHours(24))))
        .signWith(key)
        .compact();
  }

  public String extractUsername(String token) {
    // return Jwts.parserBuilder()
    // .setSigningKey(key) // clé secrète
    // .build()
    // .parseClaimsJws(token) // parse le token
    // .getBody()
    // .getSubject(); // récupère le username
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }
}
