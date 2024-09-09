package com.example.FinanceDemo.services;

import com.example.FinanceDemo.models.User;
import com.example.FinanceDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void save(User user) {
        userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean isEmailAlreadyInUse(String email) {
        return userRepo.findByEmail(email) != null;
    }

}
