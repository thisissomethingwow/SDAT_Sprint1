package com.keyin.flight;


import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Get all flights
    @GetMapping
    public List<Flight> getAllFlights(){
        return flightService.findAllFlights();
    }

    // get airports used by a specific passenger
    @GetMapping("/passenger/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsUsedByPassenger(@PathVariable Long id) {
        List<Airport> airports = flightService.getAirportsUsedByPassenger(id);
        if (airports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(airports, HttpStatus.OK);
        }
    }

    // get flight by id
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
    // add a new flight
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight newFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    // Update an existing Flight
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestBody Flight updatedFlight) {
        if (updatedFlight == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Flight updated = flightService.updateFlight(id, updatedFlight);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id){
        Flight flight = flightService.getFlightById(id);
        if (flight == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get aircraft used by a specific passenger
    @GetMapping("/passenger/{id}/aircraft")
    public ResponseEntity<List<Aircraft>> getAircraftByPassenger(@PathVariable Long id) {
        List<Aircraft> aircraft = flightService.getAircraftByPassenger(id);
        if (aircraft.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }
}
