package com.example.expensetracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "city", schema = "expense-tracker")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private String cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "region", nullable = false)
    private String region;

    // Getters and Setters
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}