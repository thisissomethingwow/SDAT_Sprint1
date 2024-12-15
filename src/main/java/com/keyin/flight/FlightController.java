package com.keyin.flight;


import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
public class FlightController {

    @Autowired
    FlightService flightService;

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
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
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
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        try {
            // Fetch the flight by ID
            Flight flight = flightService.getFlightById(id);
            if (flight == null) {
                return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
            }

            // Delete the flight
            flightService.deleteFlight(id);
            return new ResponseEntity<>("Flight deleted successfully", HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            // Handle unexpected errors
            return new ResponseEntity<>("Error deleting flight: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<Flight>> getFlightsByAirline(@PathVariable Long airlineId) {
        List<Flight> flights = flightService.getFlightsByAirline(airlineId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }



    @GetMapping("/status/{status}")
    public ResponseEntity<List<Flight>> getFlightsByStatus(@PathVariable FlightStatus status) {
        List<Flight> flights = flightService.getFlightsByStatus(status);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/departures/{airportId}")
    public ResponseEntity<List<Flight>> getDepartureFlights(@PathVariable Long airportId) {
        List<Flight> flights = flightService.getDepartureFlights(airportId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/arrivals/{airportId}")
    public ResponseEntity<List<Flight>> getArrivalFlights(@PathVariable Long airportId) {
        List<Flight> flights = flightService.getArrivalFlights(airportId);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/timerange")
    public ResponseEntity<List<Flight>> getFlightsByTimeRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<Flight> flights = flightService.getFlightsByTimeRange(start, end);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }


}
