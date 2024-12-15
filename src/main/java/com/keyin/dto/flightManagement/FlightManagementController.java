package com.keyin.dto.flightManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/flight-management")
@CrossOrigin(origins = "*")
public class FlightManagementController {

    @Autowired
    private FlightManagementService flightManagementService;

    @PostMapping("/flights")
    public ResponseEntity<?> createFlight(@RequestBody FlightManagementDTO flightDTO) {
        try {
            return ResponseEntity.ok(flightManagementService.createFlight(flightDTO));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (FlightManagementException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/reference-data")
    public ResponseEntity<?> getReferenceData() {
        try {
            return ResponseEntity.ok(flightManagementService.getReferenceData());
        } catch (FlightManagementException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id) {
        try {
            FlightManagementDTO flightDTO = flightManagementService.getFlightById(id);
            return ResponseEntity.ok(flightDTO);
        } catch (FlightManagementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Long id, @RequestBody FlightManagementDTO flightDTO) {
        try {
            FlightManagementDTO updatedFlight = flightManagementService.updateFlight(id, flightDTO);
            return ResponseEntity.ok(updatedFlight);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (FlightManagementException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}