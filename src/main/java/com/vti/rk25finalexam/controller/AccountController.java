package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountCreateDTO;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.AccountUpdateDTO;
import com.vti.rk25finalexam.service.AccountService;
import com.vti.rk25finalexam.spec.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<Page<AccountDTO>> getAll(
        AccountCriteria criteria,
        Pageable pageable
    ) {
        Page<AccountDTO> accountList = accountService.findAllByCriteria(criteria, pageable);
        return ResponseEntity
            .ok()
            .body(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountDTO>> getOne(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(accountService.getOne(id));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<AccountDTO>> getOne(@PathVariable Integer id) {
//        Optional<AccountDTO> accountDTO =
//            accountService.getOneReturnDTO(id).map(accDto -> {
//                accDto.add(
//                    WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder.methodOn(AccountController.class).getOne(id))
//                    .withSelfRel());
//                accDto.add(
//                    WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder
//                            .methodOn(DepartmentController.class)
//                            .getOne(accDto.getDepartmentId()))
//                        .withSelfRel());
//                return accDto;
//            });
//        return ResponseEntity
//            .ok()
//            .body(accountDTO);
//    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(
            @RequestBody @Validated AccountCreateDTO accountCreateDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(accountService.create(accountCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(
        @PathVariable Integer id,
        @RequestBody AccountUpdateDTO accountUpdateDTO
    ) throws NotFoundException {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(accountService.update(id, accountUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable Integer id)
        throws NotFoundException {
        AccountDTO responseAccount = accountService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseAccount);
    }
}
