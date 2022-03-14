package com.library.auth.dto;

public class AuthResponseDto {

    private long id;
    private String username;
    private String email;
    private String name;
    private String token;
    private String role;

    public AuthResponseDto(long id, String username, String email, String name, String role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.token = token;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}