package com.example.FinanceDemo.services;

import com.example.FinanceDemo.models.Transaction;
import com.example.FinanceDemo.models.TransactionType;
import com.example.FinanceDemo.repository.CategoryRepo;
import com.example.FinanceDemo.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    public List<Transaction> getRecentTransactions() {
        return transactionRepo.findTop10ByOrderByTransactionDateDesc();
    }

    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void delete(Long id) {
        transactionRepo.deleteById(id);
    }

    public Transaction findById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    public void updateTransaction(Long id, Transaction transaction) {
        if (transactionRepo.existsById(id)) {
            transaction.setId(id);
            transactionRepo.save(transaction);
        }
    }

    public BigDecimal getTotalIncome() {
        return transactionRepo.findByType(TransactionType.INCOME).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return transactionRepo.findByType(TransactionType.EXPENSE).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateBalance() {
        BigDecimal totalIncome = getTotalIncome();
        BigDecimal totalExpenses = getTotalExpenses();
        return totalIncome.subtract(totalExpenses);
    }

    public List<Transaction> findTransactions(LocalDate startDate, LocalDate endDate, String type, String category) {
        List<Transaction> transactions = transactionRepo.findAll();

        if (startDate != null) {
            transactions = transactions.stream()
                    .filter(t -> !t.getTransactionDate().isBefore(startDate))
                    .collect(Collectors.toList());
        }

        if (endDate != null) {
            transactions = transactions.stream()
                    .filter(t -> !t.getTransactionDate().isAfter(endDate))
                    .collect(Collectors.toList());
        }

        if (type != null && !type.equals("ALL")) {
            transactions = transactions.stream()
                    .filter(t -> t.getType().toString().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        if (category != null && !category.equals("ALL")) {
            transactions = transactions.stream()
                    .filter(t -> t.getCategory().getName().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        return transactions;
    }
}

