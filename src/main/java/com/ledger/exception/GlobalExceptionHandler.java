package com.ledger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAccountNotFoundException(
            AccountNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidTransferException(
            InvalidTransferException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientBalanceException(
            InsufficientBalanceException ex){
        return ex.getMessage();
    }
}