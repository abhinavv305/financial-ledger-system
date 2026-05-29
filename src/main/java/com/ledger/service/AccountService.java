package com.ledger.service;

import com.ledger.dto.CreateAccountRequest;
import com.ledger.entity.Account;
import com.ledger.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService{
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public  Account createAccount( CreateAccountRequest request){
        Account account = Account.builder()
                .ownerName(request.getOwnerName())
                .balance(request.getInitialBalance())
                .createdAt(LocalDateTime.now())
                .build();
        return accountRepository.save(account);
    }
}