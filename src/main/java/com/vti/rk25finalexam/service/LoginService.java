package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.LoginRequest;
import com.vti.rk25finalexam.entity.dto.LoginResponse;
import com.vti.rk25finalexam.entity.dto.RegisterRequest;

import java.util.Optional;

public interface LoginService {
    Optional<LoginResponse> login(LoginRequest loginRequest);

    Optional<AccountDTO> register(RegisterRequest registerRequest);
}
