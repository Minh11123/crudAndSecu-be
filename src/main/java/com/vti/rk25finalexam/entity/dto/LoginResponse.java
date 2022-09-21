package com.vti.rk25finalexam.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String role;

    public LoginResponse id(Long id) {
        this.id = id;
        return this;
    }

    public LoginResponse username(String username) {
        this.username = username;
        return this;
    }

    public LoginResponse password(String password) {
        this.password = password;
        return this;
    }

    public LoginResponse firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LoginResponse lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LoginResponse role(String role) {
        this.role = role;
        return this;
    }
}
