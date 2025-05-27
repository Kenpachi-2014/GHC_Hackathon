package com.example.expensetracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Provides an overview of available API endpoints
 */
@RestController
@RequestMapping("/api")
public class ApiInfoController {
    
    @GetMapping
    public Map<String, Object> getApiInfo() {
        Map<String, Object> apiInfo = new LinkedHashMap<>();
        
        // General API info
        apiInfo.put("name", "Expense Tracker API");
        apiInfo.put("version", "1.0.0");
        
        // Transactions endpoints
        Map<String, String> transactionEndpoints = new LinkedHashMap<>();
        transactionEndpoints.put("List all transactions", "GET /api/transactions");
        transactionEndpoints.put("Get transaction by ID", "GET /api/transactions/{id}");
        transactionEndpoints.put("Create transaction", "POST /api/transactions");
        transactionEndpoints.put("Update transaction", "PUT /api/transactions/{id}");
        transactionEndpoints.put("Delete transaction", "DELETE /api/transactions/{id}");
        transactionEndpoints.put("Get transactions by customer", "GET /api/transactions/customer/{customerId}");
        transactionEndpoints.put("Get transactions by category", "GET /api/transactions/category/{categoryId}");
        transactionEndpoints.put("Get transactions by date range", "GET /api/transactions/date-range?startDate={date}&endDate={date}");
        
        // Categories endpoints
        Map<String, String> categoryEndpoints = new LinkedHashMap<>();
        categoryEndpoints.put("List all categories", "GET /api/categories");
        categoryEndpoints.put("Get category by ID", "GET /api/categories/{id}");
        categoryEndpoints.put("Create category", "POST /api/categories");
        categoryEndpoints.put("Update category", "PUT /api/categories/{id}");
        categoryEndpoints.put("Delete category", "DELETE /api/categories/{id}");
        categoryEndpoints.put("Find categories by name", "GET /api/categories/search/name/{name}");
        categoryEndpoints.put("Find categories by type", "GET /api/categories/search/type/{type}");
        
        // Customers endpoints
        Map<String, String> customerEndpoints = new LinkedHashMap<>();
        customerEndpoints.put("List all customers", "GET /api/customers");
        customerEndpoints.put("Get customer by ID", "GET /api/customers/{id}");
        customerEndpoints.put("Create customer", "POST /api/customers");
        customerEndpoints.put("Update customer", "PUT /api/customers/{id}");
        customerEndpoints.put("Delete customer", "DELETE /api/customers/{id}");
        customerEndpoints.put("Find customers by last name", "GET /api/customers/search/lastname/{lastName}");
        customerEndpoints.put("Find customers by full name", "GET /api/customers/search/fullname?firstName={firstName}&lastName={lastName}");
        customerEndpoints.put("Find customers by city", "GET /api/customers/search/city/{cityId}");
        customerEndpoints.put("Find customers by job", "GET /api/customers/search/job/{jobId}");
        customerEndpoints.put("Find customers born before a date", "GET /api/customers/search/born-before?date={date}");
        
        // Cities endpoints
        Map<String, String> cityEndpoints = new LinkedHashMap<>();
        cityEndpoints.put("List all cities", "GET /api/cities");
        cityEndpoints.put("Get city by ID", "GET /api/cities/{id}");
        cityEndpoints.put("Create city", "POST /api/cities");
        cityEndpoints.put("Update city", "PUT /api/cities/{id}");
        cityEndpoints.put("Delete city", "DELETE /api/cities/{id}");
        cityEndpoints.put("Find cities by name", "GET /api/cities/search/name/{cityName}");
        cityEndpoints.put("Find cities by region", "GET /api/cities/search/region/{region}");
        
        // Jobs endpoints
        Map<String, String> jobEndpoints = new LinkedHashMap<>();
        jobEndpoints.put("List all jobs", "GET /api/jobs");
        jobEndpoints.put("Get job by ID", "GET /api/jobs/{id}");
        jobEndpoints.put("Create job", "POST /api/jobs");
        jobEndpoints.put("Update job", "PUT /api/jobs/{id}");
        jobEndpoints.put("Delete job", "DELETE /api/jobs/{id}");
        jobEndpoints.put("Find jobs by title", "GET /api/jobs/search/title/{jobTitle}");
        jobEndpoints.put("Find job by partial title", "GET /api/jobs/search/title-contains?partialTitle={partialTitle}");
        
        // Add all endpoints to response
        Map<String, Object> endpoints = new LinkedHashMap<>();
        endpoints.put("transactions", transactionEndpoints);
        endpoints.put("categories", categoryEndpoints);
        endpoints.put("customers", customerEndpoints);
        endpoints.put("cities", cityEndpoints);
        endpoints.put("jobs", jobEndpoints);
        
        apiInfo.put("endpoints", endpoints);
        
        return apiInfo;
    }
}
