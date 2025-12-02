package com.stockmanager.stockmanager_backend.service;

import com.stockmanager.stockmanager_backend.dto.LoginRequest;
import com.stockmanager.stockmanager_backend.dto.LoginResponse;
import com.stockmanager.stockmanager_backend.dto.RegisterRequest;
import com.stockmanager.stockmanager_backend.model.User;
import com.stockmanager.stockmanager_backend.repository.UserRepository;
import com.stockmanager.stockmanager_backend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtil jwtUtil;

  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByUsername(request.username())
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new RuntimeException("Mot de passe incorrect");
    }

    String token = jwtUtil.generateToken(user);

    return new LoginResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        token);
  }

  public void register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new RuntimeException("Email déjà utilisé");
    }

    User user = new User();
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setPassword(passwordEncoder.encode(request.password()));

    userRepository.save(user);
  }
}
