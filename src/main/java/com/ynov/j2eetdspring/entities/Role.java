package com.ynov.j2eetdspring.entities;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");


    public final String role;

    Role(String role) {
        this.role = role;
    }


    public String getRole() {
        return role;
    }
}