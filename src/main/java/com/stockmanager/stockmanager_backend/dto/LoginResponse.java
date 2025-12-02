package com.stockmanager.stockmanager_backend.dto;

public record LoginResponse(Long id, String username, String email, String token) {
}
