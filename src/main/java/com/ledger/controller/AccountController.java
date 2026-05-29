package com.ledger.controller;

import  com.ledger.dto.CreateAccountRequest;
import com.ledger.entity.Account;
import com.ledger.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")

public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    @PostMapping
    public Account createAccount(
            @Valid @RequestBody CreateAccountRequest request){
        return accountService.createAccount(request);
    }
}