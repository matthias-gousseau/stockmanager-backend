package com.stockmanager.stockmanager_backend.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
    @NotBlank(message = "Le nom d'utilisateur est obligatoire") @Size(min = 5, max = 100) String username,

    @NotBlank(message = "L'email est obligatoire") @Email(message = "L'email n'est pas valide") String email,

    @NotBlank(message = "Le mot de passe est obligatoire") @Size(min = 6, max = 100) String password) {
}
