package com.keyin.flight;


import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Get all flights
    public List<Flight> findAllFlights() {
        return (List<Flight>) flightRepository.findAll();
    }

    // Get flight by id
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // add a new flight
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }



    public void deleteFlight(Long id) {
            flightRepository.deleteById(id);
        }
    // Get airports used by a specific passenger
    public List<Airport> getAirportsUsedByPassenger(Long passengerId) {
        List<Airport> departureAirports = flightRepository.findDepartureAirportsByPassenger(passengerId);
        List<Airport> arrivalAirports = flightRepository.findArrivalAirportsByPassenger(passengerId);

        Set<Airport> uniqueAirports = new HashSet<>();
        uniqueAirports.addAll(departureAirports);
        uniqueAirports.addAll(arrivalAirports);
        return new ArrayList<>(uniqueAirports);
    }
    // Get aircraft used by a specific passenger
    public List<Aircraft> getAircraftByPassenger(Long passengerId) {
        return flightRepository.findAircraftByPassengerId(passengerId);
    }
    public List<Flight> getFlightsByAirline(Long airlineId) {
        return flightRepository.findByAirlineId(airlineId);
    }


    public List<Flight> getFlightsByStatus(FlightStatus status) {
        return flightRepository.findByFlightStatus(status);
    }

    public List<Flight> getDepartureFlights(Long airportId) {
        return flightRepository.findByDepartureAirportId(airportId);
    }

    public List<Flight> getArrivalFlights(Long airportId) {
        return flightRepository.findByArrivalAirportId(airportId);
    }

    public List<Flight> getFlightsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findFlightsByTimeRange(start, end);
    }

    // update a flight
    public Flight updateFlight(Long id, Flight updatedFlight) {
        Optional<Flight> flightToUpdateOptional = flightRepository.findById(id);

        if (flightToUpdateOptional.isPresent()) {
            Flight flightToUpdate = flightToUpdateOptional.get();

            // Update all fields
            flightToUpdate.setAircraft(updatedFlight.getAircraft());
            flightToUpdate.setDepartureAirport(updatedFlight.getDepartureAirport());
            flightToUpdate.setArrivalAirport(updatedFlight.getArrivalAirport());
            flightToUpdate.setDepartureTime(updatedFlight.getDepartureTime());
            flightToUpdate.setArrivalTime(updatedFlight.getArrivalTime());
            flightToUpdate.setPassengers(updatedFlight.getPassengers());
            flightToUpdate.setNumberOfPassengers(updatedFlight.getNumberOfPassengers());
            flightToUpdate.setAirline(updatedFlight.getAirline());
            flightToUpdate.setFlightStatus(updatedFlight.getFlightStatus());
            flightToUpdate.setDepartureGate(updatedFlight.getDepartureGate());
            flightToUpdate.setArrivalGate(updatedFlight.getArrivalGate());

            return flightRepository.save(flightToUpdate);
        }
        return null;
    }



    }
