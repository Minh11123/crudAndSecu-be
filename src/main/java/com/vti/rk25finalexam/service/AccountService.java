package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountCreateDTO;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.AccountUpdateDTO;
import com.vti.rk25finalexam.spec.Expression;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {


    Optional<AccountDTO> getOne(Integer id);

    AccountDTO create(Account account);

    AccountDTO update(Integer id, AccountUpdateDTO accountUpdateDTO);

    AccountDTO delete(Integer id) throws NotFoundException;

    Page<AccountDTO> findAllByCriteria(AccountCriteria criteria, Pageable pageable);

    AccountDTO create(AccountCreateDTO accountCreateDTO);
}
