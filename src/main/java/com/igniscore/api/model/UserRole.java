package com.igniscore.api.model;

public enum UserRole {
    OWNER("owner"),
    EMPLOYEE("employee"),
    ADMIN("admin");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
