package com.ledger.controller;

import  com.ledger.dto.CreateAccountRequest;
import com.ledger.dto.TransactionResponse;
import com.ledger.dto.TransferResponse;
import com.ledger.entity.Account;
import com.ledger.service.AccountService;
import com.ledger.dto.TransferMoneyRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }
    @PostMapping("/transfer")
    public TransferResponse transferMoney(
            @Valid @RequestBody TransferMoneyRequest request){
        return accountService.transferMoney(request);
    }
    @GetMapping("/{id}/transactions")
    public List<TransactionResponse>
    getTransactionsForAccount(
            @PathVariable Long id){
        return accountService.getTransactionsForAccount(id);
    }
}