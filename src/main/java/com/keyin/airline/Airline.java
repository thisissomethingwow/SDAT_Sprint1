package com.keyin.airline;

import com.keyin.aircraft.Aircraft;
import com.keyin.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airline {
    @Id
    @SequenceGenerator(name = "airline_sequence", sequenceName = "airline_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airline_sequence")
    private long id;

    private String name;
    private String country;
    private int fleetSize;

    @OneToMany(mappedBy = "airline",cascade = CascadeType.ALL)//this means that when in this case an airline gets saved,deleted and updated all of its aircraft change to match
    private List<Aircraft> aircraft;

    @OneToMany(mappedBy = "airline",cascade = CascadeType.ALL)
    private List<Flight> flight;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFleetSize() {
        return fleetSize;
    }

    public void setFleetSize(int fleetSize) {
        this.fleetSize = fleetSize;
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


    public Airline(String name, String country, int fleetSize, List<Aircraft> aircraft, List<Flight> flight) {
        this.name = name;
        this.country = country;
        this.fleetSize = fleetSize;
        this.aircraft = aircraft;
        this.flight = flight;
    }

    public Airline(long id, String name, String country, int fleetSize, List<Aircraft> aircraft, List<Flight> flight) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.fleetSize = fleetSize;
        this.aircraft = aircraft;
        this.flight = flight;
    }
}
