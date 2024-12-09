package com.keyin.dto.FlightBoard;

import com.keyin.flight.FlightStatus;
import java.time.LocalDateTime;

public class FlightBoardDTO {
    private Long flightId;  // New field
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String departureCity;
    private String arrivalCity;
    private String departureGate;
    private String arrivalGate;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus status;
    private int numberOfPassengers;
    private int aircraftCapacity;

    public FlightBoardDTO(Long flightId, String flightNumber, String airline, String departureAirport,
                          String arrivalAirport, String departureCity, String arrivalCity,
                          String departureGate, String arrivalGate, LocalDateTime departureTime,
                          LocalDateTime arrivalTime, FlightStatus status, int numberOfPassengers,
                          int aircraftCapacity) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureGate = departureGate;
        this.arrivalGate = arrivalGate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.numberOfPassengers = numberOfPassengers;
        this.aircraftCapacity = aircraftCapacity;
    }

    // Getters and setters
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    // Getters and setter


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }

    public String getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
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
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public int getAircraftCapacity() {
        return aircraftCapacity;
    }

    public void setAircraftCapacity(int aircraftCapacity) {
        this.aircraftCapacity = aircraftCapacity;
    }
}