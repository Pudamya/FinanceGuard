package com.example.FinanceDemo.controller;

import com.example.FinanceDemo.models.Category;
import com.example.FinanceDemo.models.TransactionType;
import com.example.FinanceDemo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/manage")
    public String manageCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category-management";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("transactionTypes", TransactionType.values());
        return "add-category";
    }

    @PostMapping("/new")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("transactionTypes", TransactionType.values());
            System.out.println("Error Category: " + result.getAllErrors());
            return "add-category";
        }
        categoryService.save(category);
        System.out.println("Category added");
        return "redirect:/categories/manage";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/categories/manage";
        }
        model.addAttribute("category", category);
        model.addAttribute("transactionTypes", TransactionType.values());
        return "edit-category";
    }

    @PostMapping("/edit")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("transactionTypes", TransactionType.values());
            System.out.println("Error Category: " + result.getAllErrors());
            return "edit-category";
        }
        categoryService.save(category);
        System.out.println("Category updated");
        return "redirect:/categories/manage";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        boolean isDeleted = categoryService.delete(id);
        if (!isDeleted) {
            model.addAttribute("errorMessage", "You can't delete this category as it is associated with transactions. Please delete associated transactions first.");
        } else {
            model.addAttribute("successMessage", "Category deleted successfully.");
        }
        model.addAttribute("categories", categoryService.findAll());
        return "category-management";
    }
}