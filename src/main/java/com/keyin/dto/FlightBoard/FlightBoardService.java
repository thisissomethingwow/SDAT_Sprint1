package com.keyin.dto.FlightBoard;

import com.keyin.flight.Flight;
import com.keyin.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightBoardService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightBoardDTO> getAllFlightBoardData() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        return flights.stream()
                .map(this::convertToFlightBoardDTO)
                .collect(Collectors.toList());
    }

    private FlightBoardDTO convertToFlightBoardDTO(Flight flight) {
        return new FlightBoardDTO(
                flight.getId(),  // Fetching flightId from the DB record
                flight.getFlightNumber(),
                flight.getAirline().getName(),
                flight.getDepartureAirport().getCode(),
                flight.getArrivalAirport().getCode(),
                flight.getDepartureAirport().getCity().getName(),
                flight.getArrivalAirport().getCity().getName(),
                flight.getDepartureGate().getGateNumber(),
                flight.getArrivalGate().getGateNumber(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getFlightStatus(),
                flight.getNumberOfPassengers(),
                flight.getAircraft().getCapacity()
        );
    }
}