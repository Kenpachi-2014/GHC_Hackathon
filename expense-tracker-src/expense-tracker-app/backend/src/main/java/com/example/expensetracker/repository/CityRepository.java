package com.example.expensetracker.repository;

import com.example.expensetracker.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends BaseRepository<City, String> {
    
    List<City> findByCityName(String cityName);
    
    List<City> findByRegion(String region);
    
    City findByCityNameAndRegion(String cityName, String region);
}
