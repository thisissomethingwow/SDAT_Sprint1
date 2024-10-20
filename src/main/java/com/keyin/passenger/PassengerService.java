package com.keyin.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;
import java.util.Optional;

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
}
