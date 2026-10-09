package com.bertozzi.loginy.dto;

import com.bertozzi.loginy.model.UserModel;
import com.bertozzi.loginy.model.UserRole;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para registro de novos usuários no sistema.
 */
public record RegisterDTO(
    @NotBlank String name,
    String email,
    UserRole role
) {
    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setName(name());
        model.setEmail(email());
        model.setRole(role());
        return model;
    }
}
