package com.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> findAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id).orElse(null);
    }

    public Aircraft addAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        Optional<Aircraft> aircraftToUpdateOptional = aircraftRepository.findById(id);
        if (aircraftToUpdateOptional.isPresent()) {
            Aircraft aircraftToUpdate = aircraftToUpdateOptional.get();
            aircraftToUpdate.setModel(updatedAircraft.getModel());
            aircraftToUpdate.setCapacity(updatedAircraft.getCapacity());
            aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            return aircraftRepository.save(aircraftToUpdate);
        }
        return null;
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }
}
