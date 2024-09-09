package com.example.FinanceDemo.controller;

import com.example.FinanceDemo.models.Transaction;
import com.example.FinanceDemo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/dashboard-data")
    @ResponseBody
    public Map<String, Object> getDashboardData() {
        BigDecimal totalIncome = transactionService.getTotalIncome();
        BigDecimal totalExpenses = transactionService.getTotalExpenses();
        BigDecimal balance = transactionService.calculateBalance();
        List<Transaction> transactions = transactionService.getRecentTransactions();


        Map<String, Double> monthlyTrends = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionDate().getMonth().name().substring(0, 3),
                        Collectors.summingDouble(t -> t.getAmount().doubleValue())
                ));

        return Map.of(
                "totalIncome", totalIncome,
                "totalExpenses", totalExpenses,
                "balance", balance,
                "monthlyTrends", monthlyTrends
        );
    }
}
