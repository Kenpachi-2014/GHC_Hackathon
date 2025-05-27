package com.example.expensetracker.repository;

import com.example.expensetracker.model.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, String> {
    
    List<Customer> findByLastName(String lastName);
    
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<Customer> findByCity_CityId(String cityId);
    
    List<Customer> findByJob_JobId(String jobId);
    
    List<Customer> findByDateOfBirthBefore(LocalDate date);
}
