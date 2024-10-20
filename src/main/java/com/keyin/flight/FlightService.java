package com.keyin.flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Get all flights
    public List<Flight> findAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    // Get flight by id
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // add a new flight
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // update a flight
    public Flight updateFlight(Long id,Flight updatedFlight) {

            Optional<Flight> flightToUpdateOptional = flightRepository.findById(id);

            if (flightToUpdateOptional.isPresent()) {
                Flight flightToUpdate = flightToUpdateOptional.get();

                // update fields
                flightToUpdate.setAircraft(updatedFlight.getAircraft());
                flightToUpdate.setDepartureAirport(updatedFlight.getDepartureAirport());
                flightToUpdate.setArrivalAirport(updatedFlight.getArrivalAirport());
                flightToUpdate.setDepartureTime(updatedFlight.getDepartureTime());
                flightToUpdate.setArrivalTime(updatedFlight.getArrivalTime());
                flightToUpdate.setPassengers(updatedFlight.getPassengers());

                return flightRepository.save(flightToUpdate);
            }
            return null; // return null if not found
        }
        // Delete flight by ID
        public void deleteFlight(Long id) {
            flightRepository.deleteById(id);
        }

}
