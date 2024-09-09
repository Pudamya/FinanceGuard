package com.example.FinanceDemo.controller;

import com.example.FinanceDemo.models.Transaction;
import com.example.FinanceDemo.models.TransactionType;
import com.example.FinanceDemo.services.CategoryService;
import com.example.FinanceDemo.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transaction-history";
    }

    @GetMapping("/new")
    public String newTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("transactionTypes", TransactionType.values());
        return "transaction-entry";
    }

    @PostMapping("/new")
    public String saveTransaction(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("transactionTypes", TransactionType.values());
            System.out.println("Error in transaction"+result.getAllErrors());
            return "transaction-entry";
        }
        transactionService.save(transaction);
        return "redirect:/transactions/history";
    }

    @GetMapping("/{id}")
    public String viewTransaction(@PathVariable("id") Long id, Model model) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return "redirect:/transactions/history";
        }
        model.addAttribute("transaction", transaction);
        return "view-transaction";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            return "redirect:/transactions/history"; // Redirect to a suitable page or handle accordingly
        }
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("transactionTypes", TransactionType.values());
        return "edit-transaction";
    }

    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable Long id, @Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("transactionTypes", TransactionType.values());
            return "edit-transaction";
        }
        transactionService.updateTransaction(id, transaction);
        return "redirect:/transactions/history";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return "redirect:/transactions/history";
    }

    @GetMapping("/history")
    public String getTransactionHistory(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "category", required = false) String category,
            Model model) {

        List<Transaction> transactions;

        boolean isFiltering = (startDate != null) || (endDate != null) || (type != null && !type.equals("ALL")) || (category != null && !category.equals("ALL"));

        if (isFiltering) {
            transactions = transactionService.findTransactions(startDate, endDate, type, category);
        } else {
            transactions = transactionService.findAll();
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("transactionTypes", TransactionType.values());
        model.addAttribute("selectedCategory", category != null ? category : "ALL");
        model.addAttribute("selectedType", type != null ? type : "ALL");
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "transaction-history";
    }
}

