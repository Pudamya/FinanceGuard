package com.example.FinanceDemo.services;

import com.example.FinanceDemo.models.Category;
import com.example.FinanceDemo.repository.CategoryRepo;
import com.example.FinanceDemo.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void save(Category category) {
        categoryRepo.save(category);
    }

    public Category findById(Long id) {
        Optional<Category> result = categoryRepo.findById(id);
        return result.orElse(null);
    }

    public boolean delete(Long id) {
        if (transactionRepo.existsByCategoryId(id)) {
            return false;
        }
        categoryRepo.deleteById(id);
        return true;
    }
}
