package com.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {
    @Autowired
    public CityServices cityServices;

    @GetMapping
    public ResponseEntity<String> getAllCity(){
        cityServices.getAllCity();
        return new ResponseEntity<>("Fetched Cities",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAllCity(Long id){
        cityServices.getAllCityById(id);
        return new ResponseEntity<>("Fetched Cities",HttpStatus.OK);
    }

    @PostMapping
    public City addCity(@RequestBody City city){
        return cityServices.addCity(city);
    }

    //update by id
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id,@RequestBody City city){
        return ResponseEntity.ok((cityServices.updateCity(id,city)));
    }
    // delete by id
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable long id){
        cityServices.deleteCityById(id);
    }



}
