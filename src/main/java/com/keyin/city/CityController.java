package com.keyin.city;

import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    public CityServices cityServices;


    // Return list of cities as JSON
    @GetMapping
    public ResponseEntity<List<City>> getAllCity(){
        Iterable<City> cityIterable = cityServices.getAllCity();
        List<City> cities = new ArrayList<>();
        cityIterable.forEach(cities::add);  // Convert Iterable to List
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Fetch city by ID and return as JSON
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        City city = cityServices.getAllCityById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);  // Return city data for the given ID
    }

    // Add new city
    @PostMapping
    public City addCity(@RequestBody City city){
        return cityServices.addCity(city);
    }

    // Update city by ID
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City city){
        return ResponseEntity.ok((cityServices.updateCity(id, city)));
    }

    // Delete city by ID
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable long id){
        cityServices.deleteCityById(id);
    }



}
