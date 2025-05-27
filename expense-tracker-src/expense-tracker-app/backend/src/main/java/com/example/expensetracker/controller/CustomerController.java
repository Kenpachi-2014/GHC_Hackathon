package com.example.expensetracker.controller;

import com.example.expensetracker.model.Customer;
import com.example.expensetracker.service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get all customers
     * @return List of all customers
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    
    /**
     * Get customer by ID
     * @param id Customer ID
     * @return Customer if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new customer
     * @param customer Customer data
     * @return Created customer with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
    
    /**
     * Update an existing customer
     * @param id Customer ID
     * @param customer Updated customer data
     * @return Updated customer, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String id, 
            @RequestBody Customer customer) {
        
        if (!customerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        customer.setCustomerId(id); // Ensure ID is set correctly
        Customer updatedCustomer = customerService.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    /**
     * Delete a customer
     * @param id Customer ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        if (!customerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Find customers by last name
     * @param lastName Last name to search
     * @return List of matching customers
     */
    @GetMapping("/search/lastname/{lastName}")
    public List<Customer> getCustomersByLastName(@PathVariable String lastName) {
        return customerService.findByLastName(lastName);
    }
    
    /**
     * Find customers by full name
     * @param firstName First name
     * @param lastName Last name
     * @return List of matching customers
     */
    @GetMapping("/search/fullname")
    public List<Customer> getCustomersByFullName(
            @RequestParam String firstName, 
            @RequestParam String lastName) {
        return customerService.findByFirstNameAndLastName(firstName, lastName);
    }
    
    /**
     * Find customers by city
     * @param cityId City ID
     * @return List of customers from the city
     */
    @GetMapping("/search/city/{cityId}")
    public List<Customer> getCustomersByCity(@PathVariable String cityId) {
        return customerService.findByCity(cityId);
    }
    
    /**
     * Find customers by job
     * @param jobId Job ID
     * @return List of customers with the job
     */
    @GetMapping("/search/job/{jobId}")
    public List<Customer> getCustomersByJob(@PathVariable String jobId) {
        return customerService.findByJob(jobId);
    }
    
    /**
     * Find customers born before a specific date
     * @param date The cutoff date
     * @return List of customers born before the date
     */
    @GetMapping("/search/born-before")
    public List<Customer> getCustomersBornBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return customerService.findByDateOfBirthBefore(date);
    }
}
