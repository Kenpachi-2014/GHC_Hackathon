package com.example.expensetracker.service;

import com.example.expensetracker.controller.model.TransactionDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    public List<TransactionDTO> listAll() {
        // return mock data for demonstration purposes  
        List<TransactionDTO> mockTransactions = new ArrayList<>();
        
        // Create several mock transactions with different categories and dates
        mockTransactions.add(createMockTransaction(
            "1001", new BigDecimal("45.75"), LocalDate.now().minusDays(1),
            LocalTime.of(10, 30), "C001", "CUST001"));
            
        mockTransactions.add(createMockTransaction(
            "1002", new BigDecimal("120.50"), LocalDate.now().minusDays(3),
            LocalTime.of(14, 15), "C002", "CUST001"));
            
        mockTransactions.add(createMockTransaction(
            "1003", new BigDecimal("67.25"), LocalDate.now().minusDays(5),
            LocalTime.of(16, 45), "C003", "CUST002"));
            
        mockTransactions.add(createMockTransaction(
            "1004", new BigDecimal("230.00"), LocalDate.now().minusDays(7),
            LocalTime.of(9, 20), "C001", "CUST003"));
            
        mockTransactions.add(createMockTransaction(
            "1005", new BigDecimal("15.99"), LocalDate.now(),
            LocalTime.of(11, 10), "C003", "CUST001"));
            
        return mockTransactions;
    }
    
    private TransactionDTO createMockTransaction(String transactionNumber, BigDecimal amount,
                                              LocalDate date, LocalTime time,
                                              String categoryId, String customerId) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(UUID.randomUUID().toString());
        dto.setTransactionNumber(transactionNumber);
        dto.setAmount(amount);
        dto.setTransactionDate(date);
        dto.setTransactionTime(time);
        dto.setCategoryId(categoryId);
        dto.setCustomerId(customerId);
        return dto;
    }
}