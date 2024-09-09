package com.example.FinanceDemo.repository;

import com.example.FinanceDemo.models.Transaction;
import com.example.FinanceDemo.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
    List<Transaction> findTop10ByOrderByTransactionDateDesc();
    List<Transaction> findByType(TransactionType transactionType);
    boolean existsByCategoryId(Long categoryId);
}

