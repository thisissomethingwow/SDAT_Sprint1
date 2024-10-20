package com.keyin.flight;

package com.keyin.aircraft;


import com.keyin.airport.Airport;
import com.keyin.passenger.Passenger;
import jakarta.persistence.*;

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
            genertator = "flight_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name= "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    private localDateTime departureTime;
    private localDateTime arrivalTime;

    @ManyToMany (mappedBy = "flights")
    private List<Passenger> passengers;

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

    public localDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(localDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public localDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(localDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
