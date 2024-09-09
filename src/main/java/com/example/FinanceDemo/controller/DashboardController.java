package com.example.FinanceDemo.controller;

import com.example.FinanceDemo.models.Transaction;
import com.example.FinanceDemo.services.TransactionService;
import com.example.FinanceDemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public DashboardController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        BigDecimal totalIncome = transactionService.getTotalIncome();
        BigDecimal totalExpenses = transactionService.getTotalExpenses();
        BigDecimal balance = transactionService.calculateBalance();

        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("balance", balance);

        List<Transaction> transactions = transactionService.getRecentTransactions();
        model.addAttribute("transactions", transactions);

        Map<String, Double> monthlyTrends = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTransactionDate().getMonth().name(),
                        Collectors.summingDouble(t -> t.getAmount().doubleValue())
                ));

        model.addAttribute("monthlyTrends", monthlyTrends);

        return "dashboard";
    }
}
