package com.keyin.gate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class GateControllerTest {
    private GateService gateService;
    private GateController gateController;

    @BeforeEach
    public void setUp() {
        gateService = Mockito.mock(GateService.class);
        gateController = new GateController();
        gateController.gateService = gateService;
    }

    @Test
    void testGetAllGate() {
        List<Gate> mockGates = new ArrayList<>();
        mockGates.add(new Gate());

        Mockito.when(gateService.getAllGate()).thenReturn(mockGates);

        List<Gate> response = gateController.getAllGate();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void testAddGate() {
        Gate gate = new Gate();
        Mockito.when(gateService.addGate(any(Gate.class))).thenReturn(gate);

        Gate response = gateController.addGate(gate);

        assertNotNull(response);
        Mockito.verify(gateService, Mockito.times(1)).addGate(any(Gate.class));
    }
}