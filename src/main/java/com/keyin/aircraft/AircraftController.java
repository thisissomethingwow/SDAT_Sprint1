package com.keyin.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircrafts")
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @GetMapping
    public List<Aircraft> getAllAircrafts() {
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
    public ResponseEntity<Aircraft> addAircraft(@RequestBody Aircraft aircraft) {
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
}
