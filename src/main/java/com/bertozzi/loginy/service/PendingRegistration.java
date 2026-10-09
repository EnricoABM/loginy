package com.bertozzi.loginy.service;

import com.bertozzi.loginy.model.UserModel;
import com.bertozzi.loginy.model.UserRole;

public record PendingRegistration(
    String name,
    String email,
    UserRole role
) {
    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        model.setRole(role);
        return model;
    }

    public static PendingRegistration from(UserModel model) {
        return new PendingRegistration(model.getName(), model.getEmail(), model.getRole());
    }
}
