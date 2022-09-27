package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.repository.AccountRepository;
import com.vti.rk25finalexam.service.QueryService;
import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
public class AccountCrudServiceImpl implements AccountCrudService {

    private final AccountRepository accountRepository;
    private final QueryService<Account> queryService;
    private  final ModelMapper modelMapper;

    public AccountCrudServiceImpl(AccountRepository accountRepository,
                                  QueryService<Account> queryService, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.queryService = queryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Account> getOne(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Page<Account> findAllByCriteria(AccountCriteria criteria, Pageable pageable) {
        Specification<Account> spec = buildWhere(criteria);
        
        return accountRepository.findAll(spec, pageable);

    }

    @Override
    public Account delete(Integer id) {
        return getOne(id)
                .map(account -> {
                    account.id(id);
                    account.isDeleted(Constants.IS_DELETED.TRUE);
                    accountRepository.save(account);
                    return save(account);
                })
                .orElse(null);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    private Specification<Account> buildWhere(AccountCriteria criteria) {
        Specification<Account> spec = Specification.where(null);

        if (criteria.getId() != null) {
            spec = spec.and(queryService.buildIntegerFilter(Constants.ACCOUNT.ID, criteria.getId()));
        }
        if (criteria.getUsername() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.ACCOUNT.USERNAME, criteria.getUsername()));
        }
        if (criteria.getFirstName() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.ACCOUNT.FIRST_NAME, criteria.getFirstName()));
        }
        if (criteria.getLastName() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.ACCOUNT.LAST_NAME, criteria.getLastName()));
        }
        if (criteria.getRole() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.ACCOUNT.ROLE, criteria.getRole()));
        }
        if (criteria.getSearch() != null) {
            Specification<Account> orSpec = Specification.where(null);
            orSpec = orSpec
                    .or(queryService.buildStringFilter(Constants.ACCOUNT.USERNAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(Constants.ACCOUNT.FIRST_NAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(Constants.ACCOUNT.LAST_NAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(Constants.ACCOUNT.ROLE, criteria.getSearch()));

            if (Utils.checkStringAsDigit(criteria.getSearch().getContains())) {
                Integer searchValue = Integer.valueOf(criteria.getSearch().getContains());
                IntegerFilter integerFilter = new IntegerFilter();
                integerFilter.setEquals(searchValue);
                orSpec.or(queryService.buildIntegerFilter(Constants.ACCOUNT.ID, integerFilter));
            }
            spec = spec.and(orSpec);
        }
        return spec;
    }
}
