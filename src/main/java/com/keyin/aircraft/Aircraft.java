package com.keyin.aircraft;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyin.airline.Airline;
import com.keyin.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Aircraft {

    @Id
    @SequenceGenerator(
            name = "aircraft_sequence",
            sequenceName = "aircraft_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aircraft_sequence"
    )
    private Long id;

    private String model;
    private int capacity;


    @OneToMany(mappedBy = "aircraft")
    @JsonManagedReference
    private List<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @JsonIgnore
    private Airline airline;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
