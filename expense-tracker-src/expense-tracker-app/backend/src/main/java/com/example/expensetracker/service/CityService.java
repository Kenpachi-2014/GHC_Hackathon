package com.example.expensetracker.service;

import com.example.expensetracker.model.City;
import com.example.expensetracker.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(String id) {
        return cityRepository.findById(id);
    }

    public List<City> findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }
    
    public List<City> findByRegion(String region) {
        return cityRepository.findByRegion(region);
    }
    
    public City findByCityNameAndRegion(String cityName, String region) {
        return cityRepository.findByCityNameAndRegion(cityName, region);
    }
    
    public City save(City city) {
        return cityRepository.save(city);
    }
    
    public void deleteById(String id) {
        cityRepository.deleteById(id);
    }
}
