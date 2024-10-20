package com.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServices {

    @Autowired
    private CityRepository cityRepository;

    public Iterable<City> getAllCity(){return cityRepository.findAll();}

    public City getAllCityById(Long id){return cityRepository.findById(id).orElse(null);}

    public City addCity(City city){return cityRepository.save(city);}

    public void deleteCityById(Long id){cityRepository.deleteById(id);}

    public City updateCity(long id,City updatedCity){
        Optional<City> cityToUpdateOptional = cityRepository.findById(id);

        if (cityToUpdateOptional.isPresent()){
            City cityToUpdate = cityToUpdateOptional.get();
            cityToUpdate.setPopulation(updatedCity.getPopulation());
            cityToUpdate.setName((updatedCity.getName()));
            return cityRepository.save(cityToUpdate);
        }
        return null;
    }


}
