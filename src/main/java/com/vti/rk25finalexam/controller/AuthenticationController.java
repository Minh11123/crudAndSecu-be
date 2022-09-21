package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.LoginRequest;
import com.vti.rk25finalexam.entity.dto.LoginResponse;
import com.vti.rk25finalexam.entity.dto.RegisterRequest;
import com.vti.rk25finalexam.service.LoginService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthenticationController {

    private final LoginService loginService;

    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/auth/register")
    ResponseEntity<Optional<AccountDTO>> login(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok().body(loginService.register(registerRequest));
    }

    @PostMapping("/auth/login")
    ResponseEntity<Optional<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.login(loginRequest));
    }
}
