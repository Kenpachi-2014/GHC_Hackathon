package com.example.expensetracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job", schema = "expense-tracker")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private String jobId;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}