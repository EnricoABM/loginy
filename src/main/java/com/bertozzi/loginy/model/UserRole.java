package com.bertozzi.loginy.model;

import java.util.List;

public enum UserRole {
    MANAGER("MANAGER", List.of(
            UserPermission.USERS_READ,
            UserPermission.USER_CREATE,
            UserPermission.USER_READ,
            UserPermission.USER_DELETE)),
    TEACHER("TEACHER", List.of(
            UserPermission.USER_READ)),
    STUDENT("STUDENT", List.of(
            UserPermission.USER_READ));

    private String role;
    private List<UserPermission> permissions;

    private UserRole(String role, List<UserPermission> permissions) {
        this.permissions = permissions;

    }

    public String getRole() {
        return role;
    }

    public List<UserPermission> getPermissions() {
        return permissions;
    }
}
