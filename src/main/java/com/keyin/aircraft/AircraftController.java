package com.keyin.aircraft;

import com.keyin.airline.Airline;
import com.keyin.airline.AirlineRepository;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;
    @Autowired
    private AirlineRepository airlineRepository;


    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return aircraftService.findAllAircrafts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        if (aircraft == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aircraft, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Aircraft> addAircraft(@RequestBody AircraftDTO aircraftDTO) {
        // Fetch the Airline entity using the provided airlineId
        Airline airline = airlineRepository.findById(aircraftDTO.getAirlineId())
            .orElseThrow(() -> new ResourceNotFoundException("Airline not found with id " + aircraftDTO.getAirlineId()));

        // Create a new Aircraft entity and set its properties
        Aircraft aircraft = new Aircraft();
        aircraft.setModel(aircraftDTO.getModel());
        aircraft.setCapacity(aircraftDTO.getCapacity());
        aircraft.setAirline(airline);

        // Save the Aircraft entity
        Aircraft newAircraft = aircraftService.addAircraft(aircraft);
        return new ResponseEntity<>(newAircraft, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(
            @PathVariable Long id,
            @RequestBody Aircraft updatedAircraft) {
        if (updatedAircraft == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Aircraft updated = aircraftService.updateAircraft(id, updatedAircraft);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAircraft, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aircraft> deleteAircraft(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        if (aircraft == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aircraftService.deleteAircraft(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{aircraftId}/airports")
    public ResponseEntity<List<Airport>> getAuthorizedAirports(@PathVariable Long aircraftId) {
        List<Airport> authorizedAirports = aircraftService.findAuthorizedAirports(aircraftId);
        if (authorizedAirports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authorizedAirports, HttpStatus.OK);
    }
//    @GetMapping("/{id}/airline-name")
//    public ResponseEntity<String> getAircraftAirlineName(@PathVariable Long id) {
//        Aircraft aircraft = aircraftService.getAircraftById(id);
//        if (aircraft == null || aircraft.getAirline() == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(aircraft.getAirline().getName(), HttpStatus.OK);
//    }
}
