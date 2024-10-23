package com.keyin.airport;


import com.keyin.city.City;
import com.keyin.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            generator = "airport_sequence"
    )
    private Long id;
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departures;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivals;

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }



}

