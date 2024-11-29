package com.keyin.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteAirlineById(Long id){airlineRepository.deleteById(id);}

    public Airline updateAirline(long id,Airline updatedAirline){
        Optional<Airline> airlineToUpdateOptional = airlineRepository.findById(id);

        if (airlineToUpdateOptional.isPresent()){
            Airline gateToUpdate = airlineToUpdateOptional.get();
            gateToUpdate.setName(updatedAirline.getName());
            gateToUpdate.setCountry((updatedAirline.getCountry()));
            gateToUpdate.setFleetSize((updatedAirline.getFleetSize()));
            gateToUpdate.setAircraft((updatedAirline.getAircraft()));
            gateToUpdate.setFlight((updatedAirline.getFlight()));
            return airlineRepository.save(gateToUpdate);
        }
        return null;
    }

}
