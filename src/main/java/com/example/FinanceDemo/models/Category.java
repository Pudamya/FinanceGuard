package com.example.FinanceDemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Category name is required")
    private String name;

    @NotNull(message = "Transaction type must be specified")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Category name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Category name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "Transaction type must be specified") TransactionType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Transaction type must be specified") TransactionType type) {
        this.type = type;
    }
}
