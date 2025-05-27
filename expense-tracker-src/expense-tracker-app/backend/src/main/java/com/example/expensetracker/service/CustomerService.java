package com.example.expensetracker.service;

import com.example.expensetracker.model.Customer;
import com.example.expensetracker.model.City;
import com.example.expensetracker.model.Job;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.CityRepository;
import com.example.expensetracker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;
    private final JobRepository jobRepository;

    public CustomerService(CustomerRepository customerRepository, 
                          CityRepository cityRepository,
                          JobRepository jobRepository) {
        this.customerRepository = customerRepository;
        this.cityRepository = cityRepository;
        this.jobRepository = jobRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }
    
    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    
    public List<Customer> findByCity(String cityId) {
        return customerRepository.findByCity_CityId(cityId);
    }
    
    public List<Customer> findByJob(String jobId) {
        return customerRepository.findByJob_JobId(jobId);
    }
    
    public List<Customer> findByDateOfBirthBefore(LocalDate date) {
        return customerRepository.findByDateOfBirthBefore(date);
    }
    
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }
}
