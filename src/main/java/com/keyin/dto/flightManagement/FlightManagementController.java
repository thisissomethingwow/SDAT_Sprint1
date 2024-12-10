package com.keyin.dto.flightManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flight-management")
@CrossOrigin
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
}