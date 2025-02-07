package com.keyin.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    // get all airports
    public List<Airport> getAllAirports() {
        return (List<Airport>) airportRepository.findAll();
    }

    // get airport by id
    public Airport getAirportById(long id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);
        return airportOptional.orElse(null);
    }
    // Add airport
    public Airport addAirport(Airport newAirport) {
        return airportRepository.save(newAirport);
    }

    // update an existing airport
    public Airport updateAirport(Long id, Airport updatedAirport) {
        Optional<Airport> airportToUpdateOptional = airportRepository.findById(id);

        if(airportToUpdateOptional.isPresent()) {
            Airport airportToUpdate = airportToUpdateOptional.get();
            // get fields
            airportToUpdate.setName(updatedAirport.getName());
            airportToUpdate.setCode(updatedAirport.getCode());
            airportToUpdate.setCity(updatedAirport.getCity());

            airportToUpdate.setSupportedAircraftTypes(updatedAirport.getSupportedAircraftTypes());

            return airportRepository.save(airportToUpdate);
        }
        return null;
    }
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
    // Method to find airports by city ID
    public List<Airport> findByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId);
    }



}
