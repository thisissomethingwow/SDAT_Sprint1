package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import  java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    // Get all passengers
    public List<Passenger> getAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    // Get passenger by ID
    public Passenger getPassengerById(Long id) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);
        return  passengerOptional.orElse(null);
    }

    // Add a new passenger
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // Update an existing passenger

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        Optional<Passenger> passengertoUpdateOptional = passengerRepository.findById(id);

        if (passengertoUpdateOptional.isPresent()) {
            Passenger passengerToUpdate = passengertoUpdateOptional.get();
            // update fields
            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName(updatedPassenger.getLastName());
            passengerToUpdate.setPhoneNumber(updatedPassenger.getPhoneNumber());
            passengerToUpdate.setCity(updatedPassenger.getCity());

            return passengerRepository.save(passengerToUpdate);
        }
        return null;
    }
    // Delete a passenger by ID
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }


    public List<Aircraft> getAircraftByPassenger(Long passengerId) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);
        if (passengerOptional.isPresent()) {
            Passenger passenger = passengerOptional.get();
            List<Flight> flights = passenger.getFlights();  // Get the flights associated with this passenger
            return flights.stream()
                    .map(Flight::getAircraft)  // Map flights to their aircraft
                    .distinct()  // Avoid duplicates if necessary
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();  // Return an empty list if no passenger is found
    }
}
