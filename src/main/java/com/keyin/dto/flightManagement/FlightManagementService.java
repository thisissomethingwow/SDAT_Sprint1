package com.keyin.dto.flightManagement;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airline.Airline;
import com.keyin.airline.AirlineService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import com.keyin.flight.Flight;
import com.keyin.flight.FlightRepository;
import com.keyin.gate.Gate;
import com.keyin.gate.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightManagementService {

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

    public FlightManagementDTO createFlight(FlightManagementDTO flightDTO) {
        validateFlightDTO(flightDTO);

        Flight flight = new Flight();
        try {
            // Set relationships using IDs from DTO
            Airline airline = airlineService.getAllAirlineById(flightDTO.getAirlineId());
            Aircraft aircraft = aircraftService.getAircraftById(flightDTO.getAircraftId());
            Airport departureAirport = airportService.getAirportById(flightDTO.getDepartureAirportId());
            Airport arrivalAirport = airportService.getAirportById(flightDTO.getArrivalAirportId());
            Gate departureGate = gateService.getGateById(flightDTO.getDepartureGateId());
            Gate arrivalGate = gateService.getGateById(flightDTO.getArrivalGateId());

            // Validate all entities were found
            validateEntitiesExist(airline, aircraft, departureAirport, arrivalAirport, departureGate, arrivalGate);

            // Validate gates belong to correct airports
            validateGateAirportAssignment(departureGate, departureAirport, "Departure");
            validateGateAirportAssignment(arrivalGate, arrivalAirport, "Arrival");

            // Set all flight properties
            flight.setAirline(airline);
            flight.setAircraft(aircraft);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureGate(departureGate);
            flight.setArrivalGate(arrivalGate);
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivalTime(flightDTO.getArrivalTime());
            flight.setFlightStatus(flightDTO.getStatus());
            flight.setNumberOfPassengers(0);

            // Save flight to get ID for flight number generation
            Flight savedFlight = flightRepository.save(flight);

            // Generate and set flight number
            savedFlight.setFlightNumber("FL" + String.format("%03d", savedFlight.getId()));

            // Save again with flight number
            savedFlight = flightRepository.save(savedFlight);

            return convertToDTO(savedFlight);

        } catch (Exception e) {
            throw new FlightManagementException("Error creating flight: " + e.getMessage());
        }
    }

    public ReferenceDataDTO getReferenceData() {
        try {
            ReferenceDataDTO referenceData = new ReferenceDataDTO();

            List<Aircraft> aircraft = aircraftService.getAllAircraft();
            List<Airline> airlines = airlineService.getAllAirlines();
            List<Airport> airports = airportService.getAllAirports();

            validateReferenceData(aircraft, airlines, airports);

            referenceData.setAircraft(aircraft.stream()
                    .map(this::convertToAircraftDTO)
                    .collect(Collectors.toList()));

            referenceData.setAirlines(airlines.stream()
                    .map(this::convertToAirlineDTO)
                    .collect(Collectors.toList()));

            referenceData.setAirports(airports.stream()
                    .map(this::convertToAirportDTO)
                    .collect(Collectors.toList()));

            Map<Long, List<GateDTO>> gatesByAirport = new HashMap<>();
            airports.forEach(airport -> {
                List<GateDTO> airportGates = gateService.getGatesByAirport(airport.getId())
                        .stream()
                        .map(this::convertToGateDTO)
                        .collect(Collectors.toList());
                gatesByAirport.put(airport.getId(), airportGates);
            });
            referenceData.setGatesByAirport(gatesByAirport);

            return referenceData;

        } catch (Exception e) {
            throw new FlightManagementException("Error fetching reference data: " + e.getMessage());
        }
    }
    public FlightManagementDTO getFlightById(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isPresent()) {
            return convertToDTO(flightOptional.get());
        } else {
            throw new FlightManagementException("Flight not found");
        }
    }

    public FlightManagementDTO updateFlight(Long id, FlightManagementDTO flightDTO) {
        validateFlightDTO(flightDTO);

        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (!flightOptional.isPresent()) {
            throw new FlightManagementException("Flight not found");
        }

        Flight flight = flightOptional.get();
        try {
            // Set relationships using IDs from DTO
            Airline airline = airlineService.getAllAirlineById(flightDTO.getAirlineId());
            Aircraft aircraft = aircraftService.getAircraftById(flightDTO.getAircraftId());
            Airport departureAirport = airportService.getAirportById(flightDTO.getDepartureAirportId());
            Airport arrivalAirport = airportService.getAirportById(flightDTO.getArrivalAirportId());
            Gate departureGate = gateService.getGateById(flightDTO.getDepartureGateId());
            Gate arrivalGate = gateService.getGateById(flightDTO.getArrivalGateId());

            // Validate all entities were found
            validateEntitiesExist(airline, aircraft, departureAirport, arrivalAirport, departureGate, arrivalGate);

            // Validate gates belong to correct airports
            validateGateAirportAssignment(departureGate, departureAirport, "Departure");
            validateGateAirportAssignment(arrivalGate, arrivalAirport, "Arrival");

            // Set all flight properties
            flight.setAirline(airline);
            flight.setAircraft(aircraft);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureGate(departureGate);
            flight.setArrivalGate(arrivalGate);
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setArrivalTime(flightDTO.getArrivalTime());
            flight.setFlightStatus(flightDTO.getStatus());

            // Save updated flight
            Flight updatedFlight = flightRepository.save(flight);

            return convertToDTO(updatedFlight);

        } catch (Exception e) {
            throw new FlightManagementException("Error updating flight: " + e.getMessage());
        }
    }

    private void validateFlightDTO(FlightManagementDTO dto) {
        if (dto == null) {
            throw new ValidationException("Flight data cannot be null");
        }
        if (dto.getDepartureTime() == null || dto.getArrivalTime() == null) {
            throw new ValidationException("Departure and arrival times must be specified");
        }
        if (dto.getDepartureTime().isAfter(dto.getArrivalTime())) {
            throw new ValidationException("Departure time cannot be after arrival time");
        }
        if (dto.getDepartureAirportId().equals(dto.getArrivalAirportId())) {
            throw new ValidationException("Departure and arrival airports cannot be the same");
        }
    }

    private void validateEntitiesExist(Airline airline, Aircraft aircraft,
                                       Airport departureAirport, Airport arrivalAirport,
                                       Gate departureGate, Gate arrivalGate) {
        if (airline == null) throw new ValidationException("Airline not found");
        if (aircraft == null) throw new ValidationException("Aircraft not found");
        if (departureAirport == null) throw new ValidationException("Departure airport not found");
        if (arrivalAirport == null) throw new ValidationException("Arrival airport not found");
        if (departureGate == null) throw new ValidationException("Departure gate not found");
        if (arrivalGate == null) throw new ValidationException("Arrival gate not found");
    }

    private void validateGateAirportAssignment(Gate gate, Airport airport, String gateType) {
        if (!Objects.equals(gate.getAirport().getId(), airport.getId())) {
            throw new ValidationException(gateType + " gate does not belong to the selected airport");
        }
    }

    private void validateReferenceData(List<Aircraft> aircraft, List<Airline> airlines, List<Airport> airports) {
        if (aircraft == null || aircraft.isEmpty()) {
            throw new ValidationException("No aircraft available in the system");
        }
        if (airlines == null || airlines.isEmpty()) {
            throw new ValidationException("No airlines available in the system");
        }
        if (airports == null || airports.isEmpty()) {
            throw new ValidationException("No airports available in the system");
        }
    }

    // Conversion methods remain the same
    private FlightManagementDTO convertToDTO(Flight flight) {
        FlightManagementDTO dto = new FlightManagementDTO();
        dto.setAirlineId(flight.getAirline().getId());
        dto.setAircraftId(flight.getAircraft().getId());
        dto.setDepartureAirportId(flight.getDepartureAirport().getId());
        dto.setArrivalAirportId(flight.getArrivalAirport().getId());
        dto.setDepartureGateId(flight.getDepartureGate().getId());
        dto.setArrivalGateId(flight.getArrivalGate().getId());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setStatus(flight.getFlightStatus());
        return dto;
    }

    private AircraftDTO convertToAircraftDTO(Aircraft aircraft) {
        return new AircraftDTO(
                aircraft.getId(),
                aircraft.getModel(),
                aircraft.getCapacity()
        );
    }

    private AirlineDTO convertToAirlineDTO(Airline airline) {
        return new AirlineDTO(
                airline.getId(),
                airline.getName()
        );
    }

    private AirportDTO convertToAirportDTO(Airport airport) {
        return new AirportDTO(
                airport.getId(),
                airport.getCode(),
                airport.getCity().getName()
        );
    }

    private GateDTO convertToGateDTO(Gate gate) {
        return new GateDTO(
                gate.getId(),
                gate.getGateNumber(),
                gate.getAirport().getId()
        );
    }
}