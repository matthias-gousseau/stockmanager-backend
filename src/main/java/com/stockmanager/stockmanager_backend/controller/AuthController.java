package com.stockmanager.stockmanager_backend.controller;

import com.stockmanager.stockmanager_backend.dto.LoginRequest;
import com.stockmanager.stockmanager_backend.dto.LoginResponse;
import com.stockmanager.stockmanager_backend.dto.RegisterRequest;
import com.stockmanager.stockmanager_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  @Autowired
  private AuthService authService;

  @GetMapping("/test")
  public String test() {
    return "API Auth OK";
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    authService.register(request);
    return ResponseEntity.ok("Utilisateur créé avec succès");
  }
}
