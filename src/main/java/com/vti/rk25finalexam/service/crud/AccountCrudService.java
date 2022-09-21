package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountCrudService {

    Optional<Account> getOne(Integer id);

    Page<Account> findAllByCriteria(AccountCriteria criteria, Pageable pageable);

    Account delete(Integer id);

    Account save(Account account);

    Optional<Account> findByUsername(String username);
}
