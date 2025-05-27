package com.example.expensetracker.service;

import com.example.expensetracker.controller.model.TransactionDTO;
import com.example.expensetracker.model.Customer;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionCategory;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.TransactionCategoryRepository;
import com.example.expensetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final TransactionCategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, 
                             CustomerRepository customerRepository,
                             TransactionCategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionDTO> listAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        
        if (transactions.isEmpty()) {
            return createMockTransactions(); // Fallback to mock data if no transactions exist
        }
        
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        // Find or create transaction category if needed
        TransactionCategory category = categoryRepository.findById(transactionDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        
        // Find customer if needed
        Customer customer = customerRepository.findById(transactionDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        
        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(transactionDTO.getTransactionNumber());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setTransactionTime(transactionDTO.getTransactionTime());
        transaction.setCategory(category);
        transaction.setCustomer(customer);
        
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }
    
    public TransactionDTO findById(String id) {
        return transactionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }
    
    public List<TransactionDTO> findByCustomerId(String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomer_CustomerId(customerId);
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TransactionDTO> findByCategoryId(String categoryId) {
        List<Transaction> transactions = transactionRepository.findByCategory_CategoryId(categoryId);
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TransactionDTO> findByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(startDate, endDate);
        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Delete a transaction by ID
     * @param id ID of the transaction to delete
     */
    public void deleteById(String id) {
        transactionRepository.deleteById(id);
    }
    
    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setTransactionNumber(transaction.getTransactionNumber());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setTransactionTime(transaction.getTransactionTime());
        dto.setCategoryId(transaction.getCategory() != null ? transaction.getCategory().getCategoryId() : null);
        dto.setCustomerId(transaction.getCustomer() != null ? transaction.getCustomer().getCustomerId() : null);
        return dto;
    }
    
    // Temporary mock data method for fallback
    private List<TransactionDTO> createMockTransactions() {
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