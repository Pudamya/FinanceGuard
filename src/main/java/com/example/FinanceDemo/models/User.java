package com.example.FinanceDemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 10, message = "Username should be between 5 to 10")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address!")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Password is required")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Username is required") @Size(min = 5, max = 10, message = "Username should be between 5 to 10") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username is required") @Size(min = 5, max = 10, message = "Username should be between 5 to 10") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Email is required") @Email(message = "Invalid email address!") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Invalid email address!") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") String password) {
        this.password = password;
    }

}

