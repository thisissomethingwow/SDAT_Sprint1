package com.keyin.dto.FlightBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightBoardControllerTest {
    private FlightBoardService flightBoardService;
    private FlightBoardController flightBoardController;

    @BeforeEach
    public void setUp() {
        flightBoardService = mock(FlightBoardService.class);
        flightBoardController = new FlightBoardController();
        flightBoardController.flightBoardService = flightBoardService;
    }

    @Test
    void testGetFlightBoard() {
        List<FlightBoardDTO> mockData = new ArrayList<>();
        mockData.add(new FlightBoardDTO());

        when(flightBoardService.getAllFlightBoardData()).thenReturn(mockData);

        ResponseEntity<List<FlightBoardDTO>> response = flightBoardController.getFlightBoard();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(flightBoardService, times(1)).getAllFlightBoardData();
    }
}