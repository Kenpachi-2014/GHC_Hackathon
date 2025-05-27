package com.example.expensetracker.controller;

import com.example.expensetracker.model.TransactionCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @GetMapping("/categories")
    public TransactionCategory[] getAllCategories() {
        return TransactionCategory.values();
    }
}