package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;
import com.vti.rk25finalexam.service.crud.AccountCrudService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountCrudService accountCrudService;

    public AuthenticationServiceImpl(AccountCrudService accountCrudService) {
        this.accountCrudService = accountCrudService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null) {
            throw new RK25Exception()
                    .rk25Error(new Rk25Error()
                            .code("authentication.username.isNotNull")
                            .param(null));
        }

        return accountCrudService.findByUsername(username)
                .map(accountDTO -> new User(
                        accountDTO.getUsername(),
                        accountDTO.getPassword(),
                        AuthorityUtils.createAuthorityList(accountDTO.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
