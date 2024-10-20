package com.keyin.airport;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Get all airports
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    // Get airport by ID
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        if (airport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    // Add a new airport
    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        if (airport == null || airport.getName() == null || airport.getCode() == null || airport.getCity() == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Airport newAirport = airportService.addAirport(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

    // Update an existing airport
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(
            @PathVariable Long id,
            @RequestBody Airport updatedAirport) {
        if (updatedAirport == null || updatedAirport.getName() == null || updatedAirport.getCode() == null || updatedAirport.getCity() == null) {
            // Return 400 if request body is invalid
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Airport updated = airportService.updateAirport(id, updatedAirport);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete an airport by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        if (airport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
