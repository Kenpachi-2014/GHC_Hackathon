package com.example.expensetracker.controller;

import com.example.expensetracker.model.City;
import com.example.expensetracker.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Get all cities
     * @return List of all cities
     */
    @GetMapping
    public List<City> getAllCities() {
        return cityService.findAll();
    }
    
    /**
     * Get city by ID
     * @param id City ID
     * @return City if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable String id) {
        Optional<City> city = cityService.findById(id);
        return city.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new city
     * @param city City data
     * @return Created city with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody City city) {
        return cityService.save(city);
    }
    
    /**
     * Update an existing city
     * @param id City ID
     * @param city Updated city data
     * @return Updated city, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable String id, 
            @RequestBody City city) {
        
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        city.setCityId(id); // Ensure ID is set correctly
        City updatedCity = cityService.save(city);
        return ResponseEntity.ok(updatedCity);
    }
    
    /**
     * Delete a city
     * @param id City ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id) {
        if (!cityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Find cities by name
     * @param cityName City name
     * @return List of matching cities
     */
    @GetMapping("/search/name/{cityName}")
    public List<City> getCitiesByName(@PathVariable String cityName) {
        return cityService.findByCityName(cityName);
    }
    
    /**
     * Find cities by region
     * @param region Region name
     * @return List of cities in the region
     */
    @GetMapping("/search/region/{region}")
    public List<City> getCitiesByRegion(@PathVariable String region) {
        return cityService.findByRegion(region);
    }
}
