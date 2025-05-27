package com.example.expensetracker.service;

import com.example.expensetracker.model.TransactionCategory;
import com.example.expensetracker.repository.TransactionCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionCategoryService {

    private final TransactionCategoryRepository categoryRepository;

    public TransactionCategoryService(TransactionCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionCategory> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<TransactionCategory> findById(String id) {
        return categoryRepository.findById(id);
    }

    public List<TransactionCategory> findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
    
    public List<TransactionCategory> findByCategoryType(String categoryType) {
        return categoryRepository.findByCategoryType(categoryType);
    }
    
    public TransactionCategory findByCategoryNameAndType(String categoryName, String categoryType) {
        return categoryRepository.findByCategoryNameAndCategoryType(categoryName, categoryType);
    }
    
    public TransactionCategory save(TransactionCategory category) {
        return categoryRepository.save(category);
    }
    
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }
}
