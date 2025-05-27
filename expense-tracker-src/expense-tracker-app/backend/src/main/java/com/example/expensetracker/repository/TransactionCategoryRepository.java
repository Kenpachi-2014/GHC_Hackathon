package com.example.expensetracker.repository;

import com.example.expensetracker.model.TransactionCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionCategoryRepository extends BaseRepository<TransactionCategory, String> {
    
    List<TransactionCategory> findByCategoryName(String categoryName);
    
    List<TransactionCategory> findByCategoryType(String categoryType);
    
    TransactionCategory findByCategoryNameAndCategoryType(String categoryName, String categoryType);
}
