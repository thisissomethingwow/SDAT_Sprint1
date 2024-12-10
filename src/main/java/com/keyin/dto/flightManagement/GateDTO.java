package com.keyin.dto.flightManagement;

public class GateDTO {
    private Long id;
    private String gateNumber;
    private Long airportId;

    public GateDTO(Long id, String gateNumber, Long airportId) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.airportId = airportId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }

    public Long getAirportId() { return airportId; }
    public void setAirportId(Long airportId) { this.airportId = airportId; }
}