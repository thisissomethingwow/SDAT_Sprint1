package com.keyin.airline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
public class AirlineControllerTest {

    @Autowired
    private AirlineController airlineController;

    private AirlineService airlineService;

    @BeforeEach
    public void setUp() {
        airlineService = Mockito.mock(AirlineService.class);
        airlineController = new AirlineController();
        airlineController.airlineService = airlineService;
    }

    @Test
    void testAddAirline() {
        Airline newAirline = new Airline("Airline ABC", new ArrayList<>(), new ArrayList<>());

        Mockito.when(airlineService.addAirline(any(Airline.class))).thenAnswer(invocation -> {
            Airline addedAirline = invocation.getArgument(0);
            addedAirline.setId(1L);
            return addedAirline;
        });

        Airline createdAirline = airlineController.addAirline(newAirline);

        assertNotNull(createdAirline);
        assertEquals("Airline ABC", createdAirline.getName());
        assertEquals(1L, createdAirline.getId());
    }

    @Test
    void testUpdateAirline() {
        Airline existingAirline = new Airline(1L, "Airline XYZ", new ArrayList<>(), new ArrayList<>());

        Airline updatedAirlineDetails = new Airline("Updated Airline XYZ", new ArrayList<>(), new ArrayList<>());

        Mockito.when(airlineService.updateAirline(anyLong(), any(Airline.class))).thenReturn(updatedAirlineDetails);

        ResponseEntity<Airline> response = airlineController.updateAirline(1L, updatedAirlineDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Airline XYZ", response.getBody().getName());
    }

    @Test
    void testGetAllAirlines() {
        List<Airline> airlineList = new ArrayList<>();
        airlineList.add(new Airline(1L, "Airline ABC", new ArrayList<>(), new ArrayList<>()));
        airlineList.add(new Airline(2L, "Airline XYZ", new ArrayList<>(), new ArrayList<>()));

        Mockito.when(airlineService.getAllAirline()).thenReturn(airlineList);

        List<Airline> airlines = airlineController.getAllAirline();

        assertNotNull(airlines);
        assertEquals(2, airlines.size());
        assertEquals("Airline ABC", airlines.get(0).getName());
        assertEquals("Airline XYZ", airlines.get(1).getName());
    }

    @Test
    void testGetAirlineById() {
        Airline airline = new Airline(1L, "Airline ABC", new ArrayList<>(), new ArrayList<>());

        Mockito.when(airlineService.getAllAirlineById(1L)).thenReturn(airline);

        ResponseEntity<Airline> response = airlineController.getAllAirlineById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Airline ABC", response.getBody().getName());
    }

    @Test
    void testGetAirlineByIdNotFound() {
        Mockito.when(airlineService.getAllAirlineById(anyLong())).thenReturn(null);

        ResponseEntity<Airline> response = airlineController.getAllAirlineById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteAirline() {
        Mockito.doNothing().when(airlineService).deleteAirlineById(anyLong());

        airlineController.deleteAirline(1L);

        Mockito.verify(airlineService, Mockito.times(1)).deleteAirlineById(1L);
    }
}
