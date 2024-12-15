package com.keyin.dto.FlightBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flightboard")
@CrossOrigin(origins = "*")
public class FlightBoardController {

    @Autowired
    public FlightBoardService flightBoardService;

    @GetMapping
    public ResponseEntity<List<FlightBoardDTO>> getFlightBoard() {
        List<FlightBoardDTO> flightBoard = flightBoardService.getAllFlightBoardData();
        return ResponseEntity.ok(flightBoard);
    }
}