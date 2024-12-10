package com.keyin.dto.flightManagement;

import java.util.List;
import java.util.Map;

public class ReferenceDataDTO {
    private List<AircraftDTO> aircraft;
    private List<AirlineDTO> airlines;
    private List<AirportDTO> airports;
    private Map<Long, List<GateDTO>> gatesByAirport;

    public List<AircraftDTO> getAircraft() {
        return aircraft;
    }

    public void setAircraft(List<AircraftDTO> aircraft) {
        this.aircraft = aircraft;
    }

    public List<AirlineDTO> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<AirlineDTO> airlines) {
        this.airlines = airlines;
    }

    public List<AirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportDTO> airports) {
        this.airports = airports;
    }

    public Map<Long, List<GateDTO>> getGatesByAirport() {
        return gatesByAirport;
    }

    public void setGatesByAirport(Map<Long, List<GateDTO>> gatesByAirport) {
        this.gatesByAirport = gatesByAirport;
    }

    // Getters and Setters (you'll need to generate these)
}