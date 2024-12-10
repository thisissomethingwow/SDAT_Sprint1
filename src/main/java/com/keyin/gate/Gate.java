package com.keyin.gate;

import com.keyin.airport.Airport;
import com.keyin.flight.Flight;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


@Entity
public class Gate {
    @Id
    @SequenceGenerator(name = "gate_sequence", sequenceName = "gate_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gate_sequence")
    private Long id;

    private String gateNumber;
    private String status;
    private String terminal;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToMany(mappedBy = "departureGate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalGate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Flight> arrivalFlights;

    // Default constructor
    public Gate() {}

    // Getters and Setters for departureFlights and arrivalFlights
    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public List<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(List<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }

    // Additional methods for other fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}
