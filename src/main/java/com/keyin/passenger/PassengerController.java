package com.keyin.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin

public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Get all passengers
    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    // Get a single passenger by ID
    @GetMapping("/passengers/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        if (passenger == null) {
            return ResponseEntity.notFound().build();  // Return 404 Not Found
        }
        return ResponseEntity.ok(passenger);  // Return 200 OK with passenger data
    }

    // Add a new passenger
    @PostMapping("/passengers")
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);  // Directly return Passenger without ResponseEntity
    }

    // Update an existing passenger
    @PutMapping("/passengers/{id}")
    public ResponseEntity<Passenger> updatePassenger(
            @PathVariable Long id,
            @RequestBody Passenger passengerDetails) {
        Passenger updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
        if (updatedPassenger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPassenger);
    }
    // Delete a passenger
    @DeleteMapping("/passengers/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}