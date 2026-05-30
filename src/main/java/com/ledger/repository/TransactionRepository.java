package com.ledger.repository;

import com.ledger.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository
        extends JpaRepository<Transaction,Long>{
    List<Transaction> findByFromAccountIdOrToAccountId(
            Long fromAccountId,
            Long toAccountId
    );
    Optional<Transaction> findByReferenceNumber(
            String referenceNumber);
}