package com.example.FinanceDemo.controller;

import com.example.FinanceDemo.models.User;
import com.example.FinanceDemo.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error register1");
            return "register";
        }
        if (userService.isEmailAlreadyInUse(user.getEmail())) {
            model.addAttribute("registerError", "Email is already in use");
            System.out.println("Error register2");
            return "register";
        }
        userService.save(user);
        System.out.println("Successfully registered");
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        User user = userService.findByUsername(username);

        if (user != null) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            if (userService.checkPassword(user, password)) {
                session.setAttribute("userId", user.getId());

                return "redirect:/dashboard";
            } else {
                System.out.println("Password mismatch!");
            }
        } else {
            System.out.println("User not found!");
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "login";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }


}




