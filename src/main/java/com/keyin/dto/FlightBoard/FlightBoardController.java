package com.keyin.dto.FlightBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flightboard")  // Keep consistent with existing path
@CrossOrigin
public class FlightBoardController {

    @Autowired
    private FlightBoardService flightBoardService;

    // Existing GET endpoint
    @GetMapping
    public ResponseEntity<List<FlightBoardDTO>> getFlightBoard() {
        List<FlightBoardDTO> flightBoard = flightBoardService.getAllFlightBoardData();
        return ResponseEntity.ok(flightBoard);
    }

    // New CRUD endpoints
    @PostMapping("/flight")
    public ResponseEntity<FlightBoardDTO> createFlight(@RequestBody FlightBoardDTO flightDTO) {
        return ResponseEntity.ok(flightBoardService.createFlight(flightDTO));
    }

    @PutMapping("/flight/{id}")
    public ResponseEntity<FlightBoardDTO> updateFlight(@PathVariable Long id, @RequestBody FlightBoardDTO flightDTO) {
        return ResponseEntity.ok(flightBoardService.updateFlight(id, flightDTO));
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightBoardService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}