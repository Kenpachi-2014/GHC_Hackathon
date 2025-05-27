package com.example.expensetracker.controller;

import com.example.expensetracker.model.City;
import com.example.expensetracker.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
@Tag(name = "City", description = "City management APIs")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Get all cities
     * @return List of all cities
     */
    @Operation(summary = "Get all cities", description = "Returns a list of all available cities in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of cities", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class)))
    @GetMapping
    public List<City> getAllCities() {
        return cityService.findAll();
    }
    
    /**
     * Get city by ID
     * @param id City ID
     * @return City if found, or 404 if not found
     */
    @Operation(summary = "Get a city by its ID", description = "Returns a single city based on its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the city",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class))),
        @ApiResponse(responseCode = "404", description = "City not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@Parameter(description = "ID of the city to retrieve") @PathVariable String id) {
        Optional<City> city = cityService.findById(id);
        return city.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new city
     * @param city City data
     * @return Created city with generated ID
     */
    @Operation(summary = "Create a new city", description = "Creates a new city and returns the created entity with assigned ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "City successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@Parameter(description = "City information for a new city to be created") @RequestBody City city) {
        return cityService.save(city);
    }
    
    /**
     * Update an existing city
     * @param id City ID
     * @param city Updated city data
     * @return Updated city, or 404 if not found
     */
    @Operation(summary = "Update an existing city", description = "Update a city with the given ID with new data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "City successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class))),
        @ApiResponse(responseCode = "404", description = "City not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @Parameter(description = "ID of the city to update") @PathVariable String id, 
            @Parameter(description = "Updated city information") @RequestBody City city) {
        
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
    @Operation(summary = "Delete a city", description = "Delete a city with the specified ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "City successfully deleted"),
        @ApiResponse(responseCode = "404", description = "City not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(
            @Parameter(description = "ID of the city to delete") @PathVariable String id) {
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
    @Operation(summary = "Find cities by name", description = "Returns all cities matching the provided name")
    @ApiResponse(responseCode = "200", description = "List of cities with matching name", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class)))
    @GetMapping("/search/name/{cityName}")
    public List<City> getCitiesByName(
            @Parameter(description = "Name of the city to search for") @PathVariable String cityName) {
        return cityService.findByCityName(cityName);
    }
    
    /**
     * Find cities by region
     * @param region Region name
     * @return List of cities in the region
     */
    @Operation(summary = "Find cities by region", description = "Returns all cities located in the specified region")
    @ApiResponse(responseCode = "200", description = "List of cities in the region", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class)))
    @GetMapping("/search/region/{region}")
    public List<City> getCitiesByRegion(
            @Parameter(description = "Region name to search for") @PathVariable String region) {
        return cityService.findByRegion(region);
    }
}
