package com.ledger.controller;

import com.ledger.dto.TransactionResponse;
import com.ledger.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final AccountService accountService;

    public TransactionController(
            AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<TransactionResponse>
    getAllTransaction(){
        return accountService
                .getAllTransactions();
    }

    @GetMapping("/{referenceNumber}")
    public TransactionResponse getTransactionByReference(
            @PathVariable String referenceNumber){
        return accountService
                .getTransactionByReference(referenceNumber);
    }
}