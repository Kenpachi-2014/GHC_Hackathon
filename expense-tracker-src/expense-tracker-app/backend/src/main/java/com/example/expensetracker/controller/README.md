# Controller Package

This package contains all REST controllers for the Expense Tracker application.

## Overview

The controllers provide REST endpoints for interacting with the Expense Tracker API. They handle HTTP requests and responses, delegating business logic to the service layer.

## Structure

- `TransactionController`: Manages transaction-related operations (add, view, update, delete)
- `CategoryController`: Manages transaction category operations
- `CustomerController`: Manages customer operations
- `CityController`: Manages city operations
- `JobController`: Manages job operations
- `ApiInfoController`: Provides an overview of available API endpoints
- `GlobalExceptionHandler`: Central exception handling for all controllers

## API Endpoints

### Transactions
- `GET /api/transactions`: List all transactions
- `GET /api/transactions/{id}`: Get transaction by ID
- `POST /api/transactions`: Create a new transaction
- `PUT /api/transactions/{id}`: Update an existing transaction
- `DELETE /api/transactions/{id}`: Delete a transaction
- `GET /api/transactions/customer/{customerId}`: Get transactions by customer
- `GET /api/transactions/category/{categoryId}`: Get transactions by category
- `GET /api/transactions/date-range?startDate={date}&endDate={date}`: Get transactions by date range

### Categories
- `GET /api/categories`: List all categories
- `GET /api/categories/{id}`: Get category by ID
- `POST /api/categories`: Create a category
- `PUT /api/categories/{id}`: Update a category
- `DELETE /api/categories/{id}`: Delete a category
- `GET /api/categories/search/name/{name}`: Find categories by name
- `GET /api/categories/search/type/{type}`: Find categories by type

### Customers
- `GET /api/customers`: List all customers
- `GET /api/customers/{id}`: Get customer by ID
- `POST /api/customers`: Create a customer
- `PUT /api/customers/{id}`: Update a customer
- `DELETE /api/customers/{id}`: Delete a customer
- Plus additional search endpoints

### Cities and Jobs
- Similar CRUD endpoints for cities and jobs

## Error Handling

All controllers use the `GlobalExceptionHandler` for consistent error responses. Error responses include:
- Status code
- Error message
- Timestamp
- Request path
- Validation errors (if applicable)

## Usage Example

To create a new transaction:

```http
POST /api/transactions
Content-Type: application/json

{
  "transactionNumber": "TX12345",
  "amount": 120.50,
  "transactionDate": "2023-05-27",
  "transactionTime": "14:30:00",
  "categoryId": "C001",
  "customerId": "CUST001"
}
```

Response:

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "transactionId": "12345678-1234-1234-1234-123456789012",
  "transactionNumber": "TX12345",
  "amount": 120.50,
  "transactionDate": "2023-05-27",
  "transactionTime": "14:30:00",
  "categoryId": "C001",
  "customerId": "CUST001"
}
```
