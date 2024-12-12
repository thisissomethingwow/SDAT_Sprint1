package com.keyin.airline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keyin.aircraft.Aircraft;
import com.keyin.flight.Flight;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airline {

    @Id
    @SequenceGenerator(name = "airline_sequence", sequenceName = "airline_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "airline_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Handles serialization of Aircraft list
    private List<Aircraft> aircraft;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore// Handles serialization of Flight list
    private List<Flight> flight;

    // Default constructor (required by Hibernate)
    public Airline() {
    }

    // Constructor without ID (for new entities)
    public Airline(String name, List<Aircraft> aircraft, List<Flight> flight) {
        this.name = name;
        this.aircraft = aircraft;
        this.flight = flight;
    }

    // Constructor with ID (for updates or specific use cases)
    public Airline(long id, String name, List<Aircraft> aircraft, List<Flight> flight) {
        this.id = id;
        this.name = name;
        this.aircraft = aircraft;
        this.flight = flight;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(List<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }

    public List<Flight> getFlight() {
        return flight;
    }

    public void setFlight(List<Flight> flight) {
        this.flight = flight;
    }
}
