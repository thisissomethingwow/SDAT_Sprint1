package com.keyin.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public Iterable<Airline> getAllAirline(){
        return airlineRepository.findAll();
    }

    public Airline getAllAirlineById(Long id){
        return airlineRepository.findById(id).orElse(null);
    }

    public Airline addAirline(Airline airline){
        return airlineRepository.save(airline);
    }

    public void deleteAirlineById(Long id){
        airlineRepository.deleteById(id);
    }

    public Airline updateAirline(long id, Airline updatedAirline){
        Optional<Airline> airlineToUpdateOptional = airlineRepository.findById(id);

        if (airlineToUpdateOptional.isPresent()){
            Airline airlineToUpdate = airlineToUpdateOptional.get();
            airlineToUpdate.setName(updatedAirline.getName());
            airlineToUpdate.setAircraft(updatedAirline.getAircraft());
            airlineToUpdate.setFlight(updatedAirline.getFlight());
            return airlineRepository.save(airlineToUpdate);
        }
        return null;
    }
    public List<Airline> getAllAirlines() {
        return (List<Airline>) airlineRepository.findAll();
    }
}