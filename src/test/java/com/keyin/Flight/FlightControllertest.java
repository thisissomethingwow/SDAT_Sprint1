package com.keyin.flight;

import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class FlightControllerTest {
    private FlightService flightService;
    private FlightController flightController;

    @BeforeEach
    public void setUp() {
        flightService = Mockito.mock(FlightService.class);
        flightController = new FlightController();
        flightController.flightService = flightService;
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = new ArrayList<>();
        Mockito.when(flightService.findAllFlights()).thenReturn(flights);

        List<Flight> response = flightController.getAllFlights();

        assertNotNull(response);
    }

    @Test
    void testGetFlightById() {
        Flight flight = new Flight();
        Mockito.when(flightService.getFlightById(anyLong())).thenReturn(flight);

        ResponseEntity<Flight> response = flightController.getFlightById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        Mockito.when(flightService.addFlight(any(Flight.class))).thenReturn(flight);

        ResponseEntity<Flight> response = flightController.addFlight(flight);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
