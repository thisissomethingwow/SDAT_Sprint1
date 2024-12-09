package com.keyin.dto.FlightBoard;

import com.keyin.aircraft.AircraftService;
import com.keyin.airline.AirlineService;
import com.keyin.airport.AirportService;
import com.keyin.flight.*;
import com.keyin.gate.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightBoardService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private GateService gateService;

    public List<FlightBoardDTO> getAllFlightBoardData() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        return flights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FlightBoardDTO createFlight(FlightBoardDTO flightDTO) {
        Flight flight = convertToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        return convertToDTO(savedFlight);
    }

    public FlightBoardDTO updateFlight(Long id, FlightBoardDTO flightDTO) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            Flight flightToUpdate = flightOptional.get();
            updateEntityFromDTO(flightToUpdate, flightDTO);
            Flight updatedFlight = flightRepository.save(flightToUpdate);
            return convertToDTO(updatedFlight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    private Flight convertToEntity(FlightBoardDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setAirline(airlineService.getAllAirlineById(flightDTO.getFlightId()));
        flight.setDepartureAirport(airportService.getAirportById(flightDTO.getFlightId()));
        flight.setArrivalAirport(airportService.getAirportById(flightDTO.getFlightId()));
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setFlightStatus(flightDTO.getStatus());
        flight.setNumberOfPassengers(flightDTO.getNumberOfPassengers());
        flight.setAircraft(aircraftService.getAircraftById(flightDTO.getFlightId()));
        flight.setDepartureGate(gateService.getGateById(flightDTO.getFlightId()));
        flight.setArrivalGate(gateService.getGateById(flightDTO.getFlightId()));
        return flight;
    }

    private FlightBoardDTO convertToDTO(Flight flight) {
        return new FlightBoardDTO(
                flight.getId(),
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

    private void updateEntityFromDTO(Flight flight, FlightBoardDTO flightDTO) {
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setAirline(airlineService.getAllAirlineById(flightDTO.getFlightId()));
        flight.setDepartureAirport(airportService.getAirportById(flightDTO.getFlightId()));
        flight.setArrivalAirport(airportService.getAirportById(flightDTO.getFlightId()));
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setFlightStatus(flightDTO.getStatus());
        flight.setNumberOfPassengers(flightDTO.getNumberOfPassengers());
        flight.setAircraft(aircraftService.getAircraftById(flightDTO.getFlightId()));
        flight.setDepartureGate(gateService.getGateById(flightDTO.getFlightId()));
        flight.setArrivalGate(gateService.getGateById(flightDTO.getFlightId()));
    }
}