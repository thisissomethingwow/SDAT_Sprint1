package com.keyin.flight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.aircraft.Aircraft;
import com.keyin.airline.Airline;
import com.keyin.airport.Airport;
import com.keyin.gate.Gate;
import com.keyin.passenger.Passenger;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {

    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            generator = "flight_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name= "aircraft_id")
    @JsonIgnore
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    @JsonIgnore
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", nullable = false)
    @JsonIgnore
    private Airport arrivalAirport;

    private int numberOfPassengers;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @JsonIgnore
    private Airline airline;

    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private List<Passenger> passengers;

//    @ManyToOne
//    @JoinColumn(name = "gate_id")
////    @JsonIgnore
//    private Gate gate;

    @ManyToOne
    @JoinColumn(name = "departure_gate_id")
    @JsonIgnore
    private Gate departureGate;

    @ManyToOne
    @JoinColumn(name = "arrival_gate_id")
    @JsonIgnore
    private Gate arrivalGate;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
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

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

//    public Gate getGate() {
//        return gate;
//    }
//
//    public void setGate(Gate gate) {
//        this.gate = gate;
//    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }
    public Gate getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(Gate departureGate) {
        this.departureGate = departureGate;
    }

    public Gate getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(Gate arrivalGate) {
        this.arrivalGate = arrivalGate;
    }
}
