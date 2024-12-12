package com.keyin.passenger;

import com.keyin.passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class PassengerControllerTest {

    @InjectMocks
    private PassengerController passengerController; // The controller to test

    @Mock
    private PassengerService passengerService; // Mocking the service layer

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setPhoneNumber("123-456-7890");
        passenger.setPassengerCity("New York");
    }

    // Test: Get all passengers
    @Test
    void testGetAllPassengers() {
        List<Passenger> passengers = Arrays.asList(passenger);
        Mockito.when(passengerService.getAllPassengers()).thenReturn(passengers);

        List<Passenger> response = passengerController.getAllPassengers();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("John", response.get(0).getFirstName());
    }

    // Test: Get a single passenger by ID
    @Test
    void testGetPassengerById() {
        Mockito.when(passengerService.getPassengerById(anyLong())).thenReturn(passenger);

        ResponseEntity<Passenger> response = passengerController.getPassengerById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testGetPassengerByIdNotFound() {
        Mockito.when(passengerService.getPassengerById(anyLong())).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.getPassengerById(999L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Test: Add a new passenger
    @Test
    void testAddPassenger() {
        Mockito.when(passengerService.addPassenger(any(Passenger.class))).thenReturn(passenger);

        passenger.setFirstName("Jane");
        passenger.setLastName("Smith");
        passenger.setPhoneNumber("987-654-3210");
        passenger.setPassengerCity("Los Angeles");

        Passenger createdPassenger = passengerController.addPassenger(passenger);
        assertEquals("Jane", createdPassenger.getFirstName());
        assertEquals("Smith", createdPassenger.getLastName());
    }

    // Test: Update an existing passenger
    @Test
    void testUpdatePassenger() {
        Mockito.when(passengerService.updatePassenger(anyLong(), any(Passenger.class))).thenReturn(passenger);

        passenger.setFirstName("UpdatedJohn");
        passenger.setLastName("UpdatedDoe");
        passenger.setPhoneNumber("000-000-0000");
        passenger.setPassengerCity("Miami");

        ResponseEntity<Passenger> response = passengerController.updatePassenger(1L, passenger);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("UpdatedJohn", response.getBody().getFirstName());
    }

    @Test
    void testUpdatePassengerNotFound() {
        Mockito.when(passengerService.updatePassenger(anyLong(), any(Passenger.class))).thenReturn(null);

        Passenger updatedPassenger = new Passenger();
        updatedPassenger.setFirstName("NonExistent");

        ResponseEntity<Passenger> response = passengerController.updatePassenger(999L, updatedPassenger);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Test: Delete a passenger
    @Test
    void testDeletePassenger() {
        Mockito.doNothing().when(passengerService).deletePassenger(anyLong());

        passengerController.deletePassenger(1L);

        Mockito.verify(passengerService, Mockito.times(1)).deletePassenger(1L);
    }

    // Test: Book flight for a passenger
    @Test
    void testBookFlight() {
        Mockito.when(passengerService.bookFlight(anyLong(), anyLong())).thenReturn(passenger);

        ResponseEntity<Passenger> response = passengerController.bookFlight(1L, 100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testBookFlightNotFound() {
        Mockito.when(passengerService.bookFlight(anyLong(), anyLong())).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.bookFlight(999L, 100L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Test: Cancel flight for a passenger
    @Test
    void testCancelFlight() {
        Mockito.when(passengerService.cancelFlight(anyLong(), anyLong())).thenReturn(passenger);

        ResponseEntity<Passenger> response = passengerController.cancelFlight(1L, 100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testCancelFlightNotFound() {
        Mockito.when(passengerService.cancelFlight(anyLong(), anyLong())).thenReturn(null);

        ResponseEntity<Passenger> response = passengerController.cancelFlight(999L, 100L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
