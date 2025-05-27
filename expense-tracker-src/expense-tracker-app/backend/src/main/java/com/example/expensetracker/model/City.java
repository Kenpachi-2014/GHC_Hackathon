package com.example.expensetracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "city", schema = "expense-tracker")
@Schema(description = "City entity representing a geographical location")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    @Schema(description = "Unique identifier of the city", example = "1")
    private String cityId;

    @Column(name = "city_name", nullable = false)
    @Schema(description = "Name of the city", example = "San Francisco")
    private String cityName;

    @Column(name = "region", nullable = false)
    @Schema(description = "Region/State/Province where the city is located", example = "California")
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