package com.bertozzi.loginy.dto;

import java.util.UUID;

import com.bertozzi.loginy.model.UserModel;
import com.bertozzi.loginy.model.UserRole;

public record UserInfoDTO(UUID id, String name, String email, UserRole role) {
    public static UserInfoDTO from(UserModel model) {
        return new UserInfoDTO(model.getId(), model.getName(), model.getEmail(), model.getRole());
    }
}
