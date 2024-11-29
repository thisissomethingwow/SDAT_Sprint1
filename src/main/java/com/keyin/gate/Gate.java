package com.keyin.gate;

import com.keyin.airport.Airport;
import com.keyin.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Gate {
    @Id
    @SequenceGenerator(name = "airline_sequence", sequenceName = "airline_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airline_sequence")
    private Long id;

    private String gateNumber;
    private String status;
    private String terminal;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;


    @OneToMany(mappedBy = "gate",cascade = CascadeType.ALL)
    private List<Flight> flights;

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

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Gate(String gateNumber, String status, String terminal, Airport airport, List<Flight> flights) {
        this.gateNumber = gateNumber;
        this.status = status;
        this.terminal = terminal;
        this.airport = airport;
        this.flights = flights;
    }

    public Gate(Long id, String gateNumber, String status, String terminal, Airport airport, List<Flight> flights) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.status = status;
        this.terminal = terminal;
        this.airport = airport;
        this.flights = flights;
    }
}
