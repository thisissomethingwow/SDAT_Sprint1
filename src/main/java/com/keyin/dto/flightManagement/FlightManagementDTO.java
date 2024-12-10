package com.keyin.dto.flightManagement;

import com.keyin.flight.FlightStatus;
import java.time.LocalDateTime;

public class FlightManagementDTO {
    private Long airlineId;
    private Long aircraftId;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private Long departureGateId;
    private Long arrivalGateId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus status = FlightStatus.Scheduled; // Default status

    // Getters and Setters (you'll need to generate these)

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public Long getDepartureGateId() {
        return departureGateId;
    }

    public void setDepartureGateId(Long departureGateId) {
        this.departureGateId = departureGateId;
    }

    public Long getArrivalGateId() {
        return arrivalGateId;
    }

    public void setArrivalGateId(Long arrivalGateId) {
        this.arrivalGateId = arrivalGateId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}