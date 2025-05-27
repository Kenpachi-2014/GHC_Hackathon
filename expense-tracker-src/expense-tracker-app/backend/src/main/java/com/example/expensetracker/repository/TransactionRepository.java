package com.example.expensetracker.repository;

import com.example.expensetracker.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, String> {
    
    List<Transaction> findByTransactionDate(LocalDate date);
    
    List<Transaction> findByCustomer_CustomerId(String customerId);
    
    List<Transaction> findByCategory_CategoryId(String categoryId);
    
    List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
    
    Transaction findByTransactionNumber(String transactionNumber);
}
