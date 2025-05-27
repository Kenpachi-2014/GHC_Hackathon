package com.example.expensetracker.controller;

import com.example.expensetracker.model.Job;
import com.example.expensetracker.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * Get all jobs
     * @return List of all jobs
     */
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.findAll();
    }
    
    /**
     * Get job by ID
     * @param id Job ID
     * @return Job if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        Optional<Job> job = jobService.findById(id);
        return job.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new job
     * @param job Job data
     * @return Created job with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {
        return jobService.save(job);
    }
    
    /**
     * Update an existing job
     * @param id Job ID
     * @param job Updated job data
     * @return Updated job, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable String id, 
            @RequestBody Job job) {
        
        if (!jobService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        job.setJobId(id); // Ensure ID is set correctly
        Job updatedJob = jobService.save(job);
        return ResponseEntity.ok(updatedJob);
    }
    
    /**
     * Delete a job
     * @param id Job ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        if (!jobService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        jobService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Find jobs by title
     * @param jobTitle Job title
     * @return List of matching jobs
     */
    @GetMapping("/search/title/{jobTitle}")
    public List<Job> getJobsByTitle(@PathVariable String jobTitle) {
        return jobService.findByJobTitle(jobTitle);
    }
    
    /**
     * Find jobs by partial title match
     * @param partialTitle Part of the job title
     * @return First matching job or empty
     */
    @GetMapping("/search/title-contains")
    public ResponseEntity<Job> getJobByTitleContaining(@RequestParam String partialTitle) {
        Optional<Job> job = jobService.findByJobTitleContaining(partialTitle);
        return job.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
