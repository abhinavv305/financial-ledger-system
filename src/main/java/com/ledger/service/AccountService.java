package com.ledger.service;

import com.ledger.dto.CreateAccountRequest;
import com.ledger.dto.TransferResponse;
import com.ledger.entity.Transaction;
import com.ledger.exception.AccountNotFoundException;
import com.ledger.entity.Account;
import com.ledger.exception.InsufficientBalanceException;
import com.ledger.exception.InvalidTransferException;
import com.ledger.repository.AccountRepository;
import com.ledger.dto.TransferMoneyRequest;
import com.ledger.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AccountService{
    private final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;

    public AccountService(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public  Account createAccount( CreateAccountRequest request){
        Account account = Account.builder()
                .ownerName(request.getOwnerName())
                .balance(request.getInitialBalance())
                .createdAt(LocalDateTime.now())
                .build();
        return accountRepository.save(account);
    }
    public Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id));
    }
    @Transactional
    public TransferResponse transferMoney(TransferMoneyRequest request){
        Account sourceAccount = accountRepository
                .findById(request.getFromAccountId())
                .orElseThrow(() ->
                    new AccountNotFoundException(
                            request.getFromAccountId()
                    ));
        Account destinationAccount = accountRepository
                .findById(request.getToAccountId())
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                request.getToAccountId()
                        ));
        if(request.getFromAccountId().equals(request.getToAccountId())){
            throw new InvalidTransferException(
                    "Transaction cannot be performed to same account"
            );
        }
        if(sourceAccount.getBalance()
                .compareTo(request.getAmount()) < 0){
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }
        sourceAccount.setBalance(
                sourceAccount.getBalance().subtract(request.getAmount())
        );
        destinationAccount.setBalance(
                destinationAccount.getBalance().add(request.getAmount())
        );
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccountId(request.getFromAccountId());
        transaction.setToAccountId(request.getToAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

        transactionRepository.save(transaction);

        TransferResponse response = new TransferResponse();

        response.setMessage("Transfer Successful");
        response.setFromAccountId(request.getFromAccountId());
        response.setToAccountId(request.getToAccountId());
        response.setAmount(request.getAmount());

        return response;
    }
}