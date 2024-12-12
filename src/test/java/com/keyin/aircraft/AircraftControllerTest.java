package com.keyin.aircraft;

import com.keyin.gate.Gate;
import com.keyin.gate.GateService;
import com.keyin.gate.GateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class AircraftControllerTest {
    private GateController gateController;
    private GateService gateService;

    @BeforeEach
    public void setUp() {
        gateService = Mockito.mock(GateService.class);
        gateController = new GateController();
        gateController.gateService = gateService;
    }

    @Test
    void testAddGate() {
        Gate newGate = new Gate();
        newGate.setGateNumber("A1");
        newGate.setStatus("Open");
        newGate.setTerminal("T1");

        Mockito.when(gateService.addGate(any(Gate.class))).thenAnswer(invocation -> {
            Gate addedGate = invocation.getArgument(0);
            addedGate.setId(1L);
            return addedGate;
        });

        Gate createdGate = gateController.addGate(newGate);

        assertNotNull(createdGate);
        assertEquals("A1", createdGate.getGateNumber());
        assertEquals("Open", createdGate.getStatus());
        assertEquals("T1", createdGate.getTerminal());
        assertEquals(1L, createdGate.getId());
    }

    @Test
    void testUpdateGate() {
        Gate existingGate = new Gate();
        existingGate.setId(1L);
        existingGate.setGateNumber("A1");
        existingGate.setStatus("Open");
        existingGate.setTerminal("T1");

        Gate updatedGateDetails = new Gate();
        updatedGateDetails.setGateNumber("A2");
        updatedGateDetails.setStatus("Closed");
        updatedGateDetails.setTerminal("T2");

        Mockito.when(gateService.updateGate(anyLong(), any(Gate.class))).thenReturn(updatedGateDetails);

        ResponseEntity<Gate> response = gateController.updateGate(1L, updatedGateDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("A2", response.getBody().getGateNumber());
        assertEquals("Closed", response.getBody().getStatus());
        assertEquals("T2", response.getBody().getTerminal());
    }

    @Test
    void testGetAllGates() {
        List<Gate> gateList = new ArrayList<>();
        Gate gate1 = new Gate();
        gate1.setId(1L);
        gate1.setGateNumber("A1");
        gate1.setStatus("Open");
        gate1.setTerminal("T1");

        Gate gate2 = new Gate();
        gate2.setId(2L);
        gate2.setGateNumber("B1");
        gate2.setStatus("Closed");
        gate2.setTerminal("T2");

        gateList.add(gate1);
        gateList.add(gate2);

        Mockito.when(gateService.getAllGate()).thenReturn(gateList);

        List<Gate> gates = gateController.getAllGate();

        assertNotNull(gates);
        assertEquals(2, gates.size());
        assertEquals("A1", gates.get(0).getGateNumber());
        assertEquals("B1", gates.get(1).getGateNumber());
    }

    @Test
    void testGetGateById() {
        Gate gate = new Gate();
        gate.setId(1L);
        gate.setGateNumber("A1");
        gate.setStatus("Open");
        gate.setTerminal("T1");

        Mockito.when(gateService.getGateById(1L)).thenReturn(gate);

        Gate responseGate = gateController.getGateById(1L);

        assertNotNull(responseGate);
        assertEquals("A1", responseGate.getGateNumber());
        assertEquals("Open", responseGate.getStatus());
        assertEquals("T1", responseGate.getTerminal());
    }

    @Test
    void testGetGateByIdNotFound() {
        Mockito.when(gateService.getGateById(anyLong())).thenReturn(null);

        Gate responseGate = gateController.getGateById(1L);

        assertNull(responseGate);
    }

    @Test
    void testDeleteGate() {
        Mockito.doNothing().when(gateService).deleteGateById(anyLong());

        gateController.deleteGate(1L);

        Mockito.verify(gateService, Mockito.times(1)).deleteGateById(1L);
    }
}
