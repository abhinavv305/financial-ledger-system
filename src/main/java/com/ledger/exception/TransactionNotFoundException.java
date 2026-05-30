package com.ledger.exception;

public class TransactionNotFoundException
        extends RuntimeException{
    public TransactionNotFoundException(String referenceNumber){
        super("Transaction not found with reference:" +referenceNumber);
    }
}