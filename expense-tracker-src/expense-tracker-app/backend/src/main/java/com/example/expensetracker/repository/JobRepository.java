package com.example.expensetracker.repository;

import com.example.expensetracker.model.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends BaseRepository<Job, String> {
    
    List<Job> findByJobTitle(String jobTitle);
    
    Optional<Job> findFirstByJobTitleContainingIgnoreCase(String partialTitle);
}
