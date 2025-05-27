package com.example.expensetracker.controller;

import com.example.expensetracker.controller.model.TransactionDTO;
import com.example.expensetracker.model.TransactionCategory;
import com.example.expensetracker.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/categories")
    public TransactionCategory[] getAllCategories() {
        return TransactionCategory.values();
    }

    @GetMapping
    public List<TransactionDTO> listAllTransactions() {
        return transactionService.listAll();
    }
}