package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.LoginRequest;
import com.vti.rk25finalexam.entity.dto.LoginResponse;
import com.vti.rk25finalexam.entity.dto.RegisterRequest;
import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;
import com.vti.rk25finalexam.service.crud.AccountCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccountCrudService accountCrudService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginServiceImpl(
            AccountCrudService accountCrudService,
            AuthenticationManager authenticationManager,
            ModelMapper modelMapper,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.accountCrudService = accountCrudService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<LoginResponse> login(LoginRequest loginRequest)  {
        validateLogin(loginRequest);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        return accountCrudService.findByUsername(loginRequest.getUsername())
                        .map(accountDTO -> modelMapper.map(accountDTO, LoginResponse.class))
                .map(loginResponse -> loginResponse.password(null));
    }

    @Override
    public Optional<AccountDTO> register(RegisterRequest registerRequest) {
        validateRegister(registerRequest);

        Account account = new Account()
                .username(registerRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .role(Constants.ROLE.EMPLOYEE);

        return Optional.of(modelMapper.map(accountCrudService.save(account), AccountDTO.class));
    }

    private void validateRegister(RegisterRequest registerRequest) {
        validateUsernamePassword(registerRequest.getUsername(), registerRequest.getPassword());
    }

    private void validateLogin(LoginRequest loginRequest) {
        validateUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    private void validateUsernamePassword(String username, String password) {
        if (username == null || username.equals("")) {
            throw new RK25Exception().rk25Error(
                    new Rk25Error().code("error.login.usernameIsNotValid")
                            .param(Collections.singleton(username))
            );
        }

        if (password == null || password.equals("")) {
            throw new RK25Exception().rk25Error(
                    new Rk25Error().code("error.login.passwordIsNotValid")
                            .param(Collections.singleton(username))
            );
        }
    }
}
