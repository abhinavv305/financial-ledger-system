package com.ledger.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id){
        super("Account not found with this id:" + id);
    }
}