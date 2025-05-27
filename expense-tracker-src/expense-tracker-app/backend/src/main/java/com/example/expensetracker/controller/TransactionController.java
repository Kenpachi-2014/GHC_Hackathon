package com.example.expensetracker.controller;

import com.example.expensetracker.controller.model.TransactionDTO;
import com.example.expensetracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Get all transactions
     * @return List of all transactions
     */
    @GetMapping
    public List<TransactionDTO> listAllTransactions() {
        return transactionService.listAll();
    }
    
    /**
     * Get transaction by ID
     * @param id Transaction ID
     * @return Transaction if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable String id) {
        TransactionDTO transaction = transactionService.findById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Create a new transaction
     * @param transactionDTO Transaction data
     * @return Created transaction with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO savedTransaction = transactionService.saveTransaction(transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Update an existing transaction
     * @param id Transaction ID
     * @param transactionDTO Updated transaction data
     * @return Updated transaction, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(
            @PathVariable String id, 
            @RequestBody TransactionDTO transactionDTO) {
        
        // Make sure the path ID matches the body ID
        if (!id.equals(transactionDTO.getTransactionId())) {
            return ResponseEntity.badRequest().build();
        }
        
        // Check if transaction exists
        if (transactionService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            TransactionDTO updatedTransaction = transactionService.saveTransaction(transactionDTO);
            return ResponseEntity.ok(updatedTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Delete a transaction
     * @param id Transaction ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        if (transactionService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Find transactions by customer ID
     * @param customerId Customer ID
     * @return List of transactions for the customer
     */
    @GetMapping("/customer/{customerId}")
    public List<TransactionDTO> getTransactionsByCustomer(@PathVariable String customerId) {
        return transactionService.findByCustomerId(customerId);
    }
    
    /**
     * Find transactions by category ID
     * @param categoryId Category ID
     * @return List of transactions for the category
     */
    @GetMapping("/category/{categoryId}")
    public List<TransactionDTO> getTransactionsByCategory(@PathVariable String categoryId) {
        return transactionService.findByCategoryId(categoryId);
    }
    
    /**
     * Find transactions between two dates
     * @param startDate Start date (inclusive)
     * @param endDate End date (inclusive)
     * @return List of transactions within the date range
     */
    @GetMapping("/date-range")
    public List<TransactionDTO> getTransactionsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return transactionService.findByDateRange(startDate, endDate);
    }
}