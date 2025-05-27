package com.example.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Base repository interface with common functionality for all entity repositories.
 * This is a marker interface that adds common query methods.
 * 
 * @param <T> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    
    /**
     * Finds all entities in the specified range for pagination.
     * 
     * @param startPosition the start position
     * @param maxResults the maximum number of results to return
     * @return a list of entities in the specified range
     */
    default List<T> findAllInRange(int startPosition, int maxResults) {
        return findAll().stream()
                .skip(startPosition)
                .limit(maxResults)
                .toList();
    }
}
