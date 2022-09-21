package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountCreateDTO;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.AccountUpdateDTO;
import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vti.rk25finalexam.service.crud.AccountCrudService;
import com.vti.rk25finalexam.service.crud.DepartmentCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final ModelMapper modelMapper;
    private final DepartmentCrudService departmentService;
    private final AccountCrudService accountCrudService;

    public AccountServiceImpl(ModelMapper modelMapper,
                              DepartmentCrudService departmentService,
                              AccountCrudService accountCrudService) {
        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
        this.accountCrudService = accountCrudService;
    }

    @Override
    public Optional<AccountDTO> getOne(Integer id) {
        return accountCrudService.getOne(id).map(acc -> modelMapper.map(acc, AccountDTO.class));
    }

    @Override
    public AccountDTO create(Account account) {
        return Optional.ofNullable(accountCrudService.save(account))
                .map(acc -> modelMapper.map(acc, AccountDTO.class))
                .orElse(null);
    }

    @Override
    public AccountDTO update(
            Integer id,
            AccountUpdateDTO accountUpdateDTO
    ) {
        // check id của account có tồn tại hay không?
        // nếu có -> update dữ liệu của account thành các dữ liệu mới trong accountUpdateDTO
        //           nếu departmentId == null -> set department của account về null
        //           nếu departmentId != null -> update department mới cho account
        // nếu không có account ->  return null;

        return getOne(id)
                .map(account -> modelMapper.map(accountUpdateDTO, Account.class))
                .map(account -> account.id(id))
                .map(account -> {
                    Optional.ofNullable(accountUpdateDTO.getDepartmentId())
                            .flatMap(departmentdId -> departmentService.getOne(departmentdId)
                                    .map(department -> {
                                        account.department(department);
                                        return account;
                                    })).orElseGet(() -> account.department(null));
                    return account;
                })
                .map(accountCrudService::save)
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .orElse(null);
    }

    @Override
    public AccountDTO delete(Integer id) {
        return Optional.ofNullable(accountCrudService.delete(id))
                .map(acc -> modelMapper.map(acc, AccountDTO.class))
                .orElse(null);
    }

    @Override
    public Page<AccountDTO> findAllByCriteria(
            AccountCriteria criteria,
            Pageable pageable) {

        Page<Account> page = accountCrudService.findAllByCriteria(criteria, pageable);

        List<AccountDTO> accountDtoList = page.getContent()
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(accountDtoList, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public AccountDTO create(AccountCreateDTO accountCreateDTO) {
        validateCreate(accountCreateDTO);
        return departmentService.getOne(accountCreateDTO.getDepartmentId())
                .map(department -> {
                    Account account =
                            modelMapper.map(accountCreateDTO, Account.class)
                                    .id(null)
                                    .department(department);
                    return create(account);
                }).map(account -> modelMapper.map(account, AccountDTO.class))
                .orElse(null);
    }

    private void validateCreate(AccountCreateDTO accountCreateDTO) {
        validateCreateUsername(accountCreateDTO.getUsername());
        validateRole(accountCreateDTO.getRole());
        validateDepartment(accountCreateDTO.getDepartmentId());
    }

    private void validateDepartment(Integer departmentId) {
        Optional.ofNullable(departmentId)
                .map(deptId -> {
                    departmentService
                            .getOne(departmentId)
                            .orElseThrow(
                                    () -> new RK25Exception()
                                            .rk25Error(new Rk25Error()
                                                    .code("account.departmentId.isNotExisted")
                                                    .param(departmentId)));
                    return deptId;
                }).orElseThrow(() -> new RK25Exception()
                .rk25Error(new Rk25Error()
                        .code("account.departmentId.isNull")
                        .param(departmentId)));
    }

    private void validateRole(String role) {
        if (Constants.ROLE.ADMIN.equals(role) ||
                Constants.ROLE.EMPLOYEE.equals(role) ||
                Constants.ROLE.MANAGER.equals(role)) {
            return;
        }
        throw new RK25Exception()
                .rk25Error(new Rk25Error()
                        .code("account.role.isNotValid")
                        .param(role));
    }

    private void validateCreateUsername(String username) {
        // check username có tồn tại trong hệ thông
        accountCrudService.findByUsername(username)
                .map(account -> {
                    throw new RK25Exception()
                            .rk25Error(new Rk25Error()
                                    .code("account.username.usernameIsExisted")
                                    .param(username));
                });

        // check username không được chứa khoảng trắng
        // TODO
    }

}
