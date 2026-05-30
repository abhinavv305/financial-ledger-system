package com.ledger.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;

    public Long getId(){
        return id;
    }

    public Long getFromAccountId(){
        return fromAccountId;
    }
    public void setFromAccountId(Long fromAccountId){
        this.fromAccountId = fromAccountId;
    }
    public Long getToAccountId(){
        return toAccountId;
    }
    public void setToAccountId(Long toAccountId){
        this.toAccountId = toAccountId;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}