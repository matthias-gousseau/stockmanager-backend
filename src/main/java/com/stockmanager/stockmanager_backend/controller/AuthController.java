package com.stockmanager.stockmanager_backend.controller;

import com.stockmanager.stockmanager_backend.dto.LoginRequest;
import com.stockmanager.stockmanager_backend.dto.LoginResponse;
import com.stockmanager.stockmanager_backend.dto.RegisterRequest;
import com.stockmanager.stockmanager_backend.dto.RegisterResponse;
import com.stockmanager.stockmanager_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  @Autowired
  private AuthService authService;

  // ---------------- LOGIN ----------------
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  // ---------------- REGISTER ----------------
  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
    RegisterResponse response = authService.register(request);
    return ResponseEntity.ok(response);
  }
}
