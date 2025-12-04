package com.stockmanager.stockmanager_backend.service;

import com.stockmanager.stockmanager_backend.dto.LoginRequest;
import com.stockmanager.stockmanager_backend.dto.LoginResponse;
import com.stockmanager.stockmanager_backend.dto.RegisterRequest;
import com.stockmanager.stockmanager_backend.dto.RegisterResponse;
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

  // ---------------- LOGIN ----------------
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

  // ---------------- REGISTER ----------------
  public RegisterResponse register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.email())) {
      throw new RuntimeException("Email déjà utilisé");
    }

    // Crée l'utilisateur avec un mapper
    User user = UserMapper.fromRegisterRequest(request, passwordEncoder);

    userRepository.save(user);

    // Retourne le DTO de réponse
    return UserMapper.toRegisterResponse(user);
  }

  // ---------------- MAPPER INTERNE ----------------
  private static class UserMapper {

    public static User fromRegisterRequest(RegisterRequest request, PasswordEncoder encoder) {
      User user = new User();
      user.setUsername(request.username());
      user.setEmail(request.email());
      user.setPassword(encoder.encode(request.password()));
      return user;
    }

    public static RegisterResponse toRegisterResponse(User user) {
      return new RegisterResponse(user.getId(), user.getUsername(), user.getEmail());
    }
  }
}
