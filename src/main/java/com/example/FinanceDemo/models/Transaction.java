package com.example.FinanceDemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotEmpty(message = "Description must not be empty")
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "Amount cannot be null") @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount cannot be null") @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero") BigDecimal amount) {
        this.amount = amount;
    }

    public @NotEmpty(message = "Description must not be empty") String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty(message = "Description must not be empty") String description) {
        this.description = description;
    }

    public @NotNull LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(@NotNull LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public @NotNull TransactionType getType() {
        return type;
    }

    public void setType(@NotNull TransactionType type) {
        this.type = type;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }
}


