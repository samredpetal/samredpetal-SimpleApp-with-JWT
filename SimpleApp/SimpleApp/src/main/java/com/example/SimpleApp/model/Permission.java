package com.example.SimpleApp.model;

public enum Permission {
    PERSONS_READ("persons:read"),
    PERSONS_WRITE("persons:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
