# Repository Package

This package contains all repository interfaces for the Expense Tracker application.

## Overview

The repository layer provides access to data stored in the database using Spring Data JPA. Each entity in the model package has a corresponding repository interface that defines methods for retrieving, creating, updating, and deleting data.

## Structure

- `BaseRepository.java`: A base interface that all other repositories extend, providing common functionality.
- `TransactionRepository.java`: Repository for Transaction entities.
- `CustomerRepository.java`: Repository for Customer entities.
- `TransactionCategoryRepository.java`: Repository for TransactionCategory entities.
- `CityRepository.java`: Repository for City entities.
- `JobRepository.java`: Repository for Job entities.

## Usage

These repositories are automatically implemented by Spring Data JPA and can be injected into service classes:

```java
@Service
public class SomeService {
    private final TransactionRepository transactionRepository;
    
    public SomeService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    
    // Use the repository to perform operations
}
```

## Custom Query Methods

Spring Data JPA automatically implements query methods based on their names. For example:

- `findByCustomer_CustomerId(String customerId)` finds transactions by customer ID.
- `findByTransactionDateBetween(LocalDate startDate, LocalDate endDate)` finds transactions within a date range.

## Additional Features

The `BaseRepository` interface introduces common functionality shared across all repositories:

```java
List<T> findAllInRange(int startPosition, int maxResults);
```

This method provides basic pagination support.
